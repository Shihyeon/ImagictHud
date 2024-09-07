package kr.shihyeon.imagicthud.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.categories.indicator.groups.enums.IndicatorMode;
import kr.shihyeon.imagicthud.gui.render.ResourceRenderer;
import kr.shihyeon.imagicthud.gui.render.TextRenderer;
import kr.shihyeon.imagicthud.util.EntityTracker;
import kr.shihyeon.imagicthud.util.FormatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.joml.Matrix4f;

public class Indicator {

    private static final float INDICATOR_SCALE = 0.025f;
    private static final float HEIGHT_OFFSET = 0.5f;
    private static final float PLAYER_HEIGHT_OFFSET = 0.15f;
    private static final float NAME_HEIGHT_OFFSET = 0.14f;
    private static final float TEXT_OFFSET = 0.00001f;

    public static void renderIndicator(LivingEntity livingEntity, float yaw, float tickDelta,
                                       PoseStack matrixStack, MultiBufferSource vertexConsumerProvider,
                                       int light, Minecraft client, ImagictHudConfig config) {

        if (config.indicator.general.enableIndicator && !EntityTracker.isInvalid(livingEntity)) {
            if (EntityTracker.isInUUIDS(livingEntity)) {
                switch (config.indicator.display.indicatorMode) {
                    case BAR_AND_NAME_A, BAR_AND_NAME_B -> {
                        renderHealthBarAndNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                        renderNameAndBackground(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                    }
                    case BAR -> renderHealthBarAndNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                    case null -> {
                        renderHealthBarAndNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                        renderNameAndBackground(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                    }
                }

            }
        }
    }

    private static void renderHealthBarAndNumber(LivingEntity livingEntity, float yaw, float tickDelta,
                                                 PoseStack matrixStack, MultiBufferSource vertexConsumerProvider,
                                                 int light, Minecraft client, ImagictHudConfig config) {

        switch (config.indicator.display.indicatorBarMode) {
            case BAR_AND_NUMBER -> {
                renderHealthBar(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                renderHealthNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
            }
            case BAR -> renderHealthBar(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
            case null -> {
                renderHealthBar(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                renderHealthNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
            }
        }
    }

    private static void renderNameAndBackground(LivingEntity livingEntity, float yaw, float tickDelta,
                                                PoseStack matrixStack, MultiBufferSource vertexConsumerProvider,
                                                int light, Minecraft client, ImagictHudConfig config) {

        if (!livingEntity.isAlwaysTicking()) {
            renderNameBackground(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
            renderName(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
        }
    }

    private static void renderHealthBar(LivingEntity livingEntity, float yaw, float tickDelta,
                                        PoseStack matrixStack, MultiBufferSource vertexConsumerProvider,
                                        int light, Minecraft client, ImagictHudConfig config) {
        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder vertexConsumer;

        float currentHealthRed = livingEntity.getHealth();
        float currentHealthYellow = livingEntity.isAlwaysTicking() ? livingEntity.getAbsorptionAmount() : livingEntity.getMaxAbsorption();
        float maxHealthRed = livingEntity.getMaxHealth();
        float maxHealthYellow = currentHealthYellow;
        float totalMaxHealth = maxHealthRed + maxHealthYellow;
        float percentageHealthRed = currentHealthRed / totalMaxHealth;
        float percentageHealthYellow = currentHealthYellow / totalMaxHealth;

        float scale = INDICATOR_SCALE;
        float barHeightOffset = livingEntity.isAlwaysTicking() ? HEIGHT_OFFSET + PLAYER_HEIGHT_OFFSET : HEIGHT_OFFSET;
        float configOffset = (float) config.indicator.layout.positionY / 100.f;
        float entityHeight = livingEntity.getBbHeight() + barHeightOffset + configOffset;

        matrixStack.pushPose();
        vertexConsumer = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        matrixStack.translate(0, entityHeight, 0);
        matrixStack.mulPose(client.getEntityRenderDispatcher().cameraOrientation());
        matrixStack.scale(scale, -scale, scale);
        Matrix4f matrix4f = matrixStack.last().pose();

        ResourceRenderer.drawEntityBar(matrix4f, vertexConsumer, percentageHealthRed, percentageHealthYellow);

        MeshData builtBuffer;
        try {
            builtBuffer = vertexConsumer.build();
            if(builtBuffer != null){
                BufferUploader.drawWithShader(builtBuffer);
                builtBuffer.close();
            }
        } catch (Exception e){
            // Handle exception if needed
        }

        RenderSystem.disableBlend();
        matrixStack.popPose();
    }

    private static void renderHealthNumber(LivingEntity livingEntity, float yaw, float tickDelta,
                                           PoseStack matrixStack, MultiBufferSource vertexConsumerProvider,
                                           int light, Minecraft client, ImagictHudConfig config) {

        float currentHealthRed = livingEntity.getHealth();
        float currentHealthYellow = livingEntity.isAlwaysTicking() ? livingEntity.getAbsorptionAmount() : livingEntity.getMaxAbsorption();
        float maxHealthRed = livingEntity.getMaxHealth();

        float scale = INDICATOR_SCALE * 2.f / 7.f;
        float numberHeightOffset = livingEntity.isAlwaysTicking() ? HEIGHT_OFFSET + PLAYER_HEIGHT_OFFSET : HEIGHT_OFFSET;
        float configOffset = (float) config.indicator.layout.positionY / 100.f;
        float entityHeight = livingEntity.getBbHeight() + numberHeightOffset + configOffset;

        boolean shadow = config.indicator.text.enableTextShadows;

        float dx = Mth.sign(client.player.getX() - livingEntity.getX());
        float dy = Mth.sign(client.player.getY() - livingEntity.getY());
        float dz = Mth.sign(client.player.getZ() - livingEntity.getZ());

        float xOffset = TEXT_OFFSET * dx;
        float yOffset = TEXT_OFFSET * dy;
        float zOffset = TEXT_OFFSET * dz;

        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, zOffset); // bar-text offset
        matrixStack.translate(0, entityHeight, 0);
        matrixStack.mulPose(client.getEntityRenderDispatcher().cameraOrientation());
        matrixStack.scale(scale, -scale, scale);

        Matrix4f matrix4f = matrixStack.last().pose();

        String formattedHealthRed = FormatUtil.formatHealthFloat(currentHealthRed);
        String formattedHealthYellow = FormatUtil.formatHealthFloat(currentHealthYellow);
        String formattedMaxHealthRed = FormatUtil.formatHealthFloat(maxHealthRed);

        String healthRedText = formattedHealthRed + " / " + formattedMaxHealthRed;
        String healthYellowText = " (+" + formattedHealthYellow + ")";

        boolean absorption = currentHealthYellow > 0;

        TextRenderer.drawEntityHealth(client, matrix4f, vertexConsumerProvider, healthRedText, healthYellowText, absorption, shadow);

        matrixStack.popPose();
    }

    private static void renderName(LivingEntity livingEntity, float yaw, float tickDelta,
                                   PoseStack matrixStack, MultiBufferSource vertexConsumerProvider,
                                   int light, Minecraft client, ImagictHudConfig config) {

        String name = livingEntity.getName().getString();

        float configScale = config.indicator.layout.nameScale == 0 ? 1.f : config.indicator.layout.nameScale / 2.f;
        float scale = INDICATOR_SCALE / 3.f * configScale;
        float nameHeightOffset = config.indicator.display.indicatorMode == IndicatorMode.BAR_AND_NAME_B ? HEIGHT_OFFSET : HEIGHT_OFFSET + NAME_HEIGHT_OFFSET;
        float configOffset = (float) config.indicator.layout.positionY / 100.f;
        float entityHeight = livingEntity.getBbHeight() + nameHeightOffset + configOffset;
        float y = config.indicator.display.indicatorMode == IndicatorMode.BAR_AND_NAME_B ? -11.2f : 0;

        float dx = Mth.sign(client.player.getX() - livingEntity.getX());
        float dy = Mth.sign(client.player.getY() - livingEntity.getY());
        float dz = Mth.sign(client.player.getZ() - livingEntity.getZ());

        float xOffset = TEXT_OFFSET * dx;
        float yOffset = TEXT_OFFSET * dy;
        float zOffset = TEXT_OFFSET * dz;

        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, zOffset); // name-text offset
        matrixStack.translate(0, entityHeight, 0);
        matrixStack.mulPose(client.getEntityRenderDispatcher().cameraOrientation());
        matrixStack.scale(scale, -scale, scale);

        Matrix4f matrix4f = matrixStack.last().pose();

        TextRenderer.drawEntityName(client, matrix4f, vertexConsumerProvider, name, y, false);

        matrixStack.popPose();
    }

    private static void renderNameBackground(LivingEntity livingEntity, float yaw, float tickDelta,
                                   PoseStack matrixStack, MultiBufferSource vertexConsumerProvider,
                                   int light, Minecraft client, ImagictHudConfig config) {

        Tesselator tessellator = Tesselator.getInstance();
        BufferBuilder vertexConsumer;

        String name = livingEntity.getName().getString();

        float configScale = config.indicator.layout.nameScale == 0 ? 1.f : config.indicator.layout.nameScale / 2.f;
        float scale = INDICATOR_SCALE / 3.f * configScale;
        float nameHeightOffset = config.indicator.display.indicatorMode == IndicatorMode.BAR_AND_NAME_B ? HEIGHT_OFFSET : HEIGHT_OFFSET + NAME_HEIGHT_OFFSET;
        float configOffset = (float) config.indicator.layout.positionY / 100.f;
        float entityHeight = livingEntity.getBbHeight() + nameHeightOffset + configOffset;
        float y = config.indicator.display.indicatorMode == IndicatorMode.BAR_AND_NAME_B ? -11.2f : 0;

        matrixStack.pushPose();
        vertexConsumer = tessellator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        matrixStack.translate(0, entityHeight, 0);
        matrixStack.mulPose(client.getEntityRenderDispatcher().cameraOrientation());
        matrixStack.scale(scale, -scale, scale);

        Matrix4f matrix4f = matrixStack.last().pose();

        ResourceRenderer.drawEntityNameBackground(matrix4f, vertexConsumer, name, y, client);

        MeshData builtBuffer;
        try {
            builtBuffer = vertexConsumer.build();
            if(builtBuffer != null){
                BufferUploader.drawWithShader(builtBuffer);
                builtBuffer.close();
            }
        } catch (Exception e){
            // Handle exception if needed
        }

        RenderSystem.disableBlend();
        matrixStack.popPose();
    }
}