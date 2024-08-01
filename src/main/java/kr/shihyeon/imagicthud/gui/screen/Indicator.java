package kr.shihyeon.imagicthud.gui.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.gui.render.ResourceRenderer;
import kr.shihyeon.imagicthud.gui.render.TextRenderer;
import kr.shihyeon.imagicthud.util.EntityTracker;
import kr.shihyeon.imagicthud.util.FormatUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.joml.Matrix4f;

public class Indicator {

    private static final float INDICATOR_SCALE = 0.025f;
    private static final float HEIGHT_OFFSET = 0.5f;
    private static final float PLAYER_HEIGHT_OFFSET = 0.15f;
    private static final float TEXT_OFFSET = 0.00001f;

    public static void renderIndicator(LivingEntity livingEntity, float yaw, float tickDelta,
                                       MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                                       int light, MinecraftClient client, ImagictHudConfig config) {

        if (config.indicator.general.enableIndicator && !EntityTracker.isInvalid(livingEntity)) {
            if (EntityTracker.isInUUIDS(livingEntity)) {
                switch (config.indicator.display.indicatorMode) {
                    case BAR_AND_NUMBER -> renderHealthBarAndNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                    case BAR -> renderHealthBar(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                    case null, default -> renderHealthBarAndNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
                }
            }
        }
    }

    private static void renderHealthBarAndNumber(LivingEntity livingEntity, float yaw, float tickDelta,
                                        MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                                        int light, MinecraftClient client, ImagictHudConfig config) {

        renderHealthBar(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
        renderHealthNumber(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client, config);
    }

    private static void renderHealthBar(LivingEntity livingEntity, float yaw, float tickDelta,
                                        MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                                        int light, MinecraftClient client, ImagictHudConfig config) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexConsumer;

        float currentHealthRed = livingEntity.getHealth();
        float currentHealthYellow = livingEntity.isPlayer() ? livingEntity.getAbsorptionAmount() : livingEntity.getMaxAbsorption();
        float maxHealthRed = livingEntity.getMaxHealth();
        float maxHealthYellow = currentHealthYellow;
        float totalMaxHealth = maxHealthRed + maxHealthYellow;
        float percentageHealthRed = currentHealthRed / totalMaxHealth;
        float percentageHealthYellow = currentHealthYellow / totalMaxHealth;

        float scale = INDICATOR_SCALE;
        float barHeightOffset = livingEntity.isPlayer() ? HEIGHT_OFFSET + PLAYER_HEIGHT_OFFSET : HEIGHT_OFFSET;
        float configOffset = (float) config.indicator.layout.positionY / 100.f;
        float entityHeight = livingEntity.getHeight() + barHeightOffset + configOffset;

        matrixStack.push();
        vertexConsumer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        matrixStack.translate(0, entityHeight, 0);
        matrixStack.multiply(client.getEntityRenderDispatcher().getRotation());
        matrixStack.scale(scale, -scale, scale);
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

        ResourceRenderer.drawEntityBar(matrix4f, vertexConsumer, percentageHealthRed, percentageHealthYellow);

        BuiltBuffer builtBuffer;
        try {
            builtBuffer = vertexConsumer.build();
            if(builtBuffer != null){
                BufferRenderer.drawWithGlobalProgram(builtBuffer);
                builtBuffer.close();
            }
        } catch (Exception e){
            // Handle exception if needed
        }

        matrixStack.pop();
    }

    private static void renderHealthNumber(LivingEntity livingEntity, float yaw, float tickDelta,
                                           MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                                           int light, MinecraftClient client, ImagictHudConfig config) {

        float currentHealthRed = livingEntity.getHealth();
        float currentHealthYellow = livingEntity.isPlayer() ? livingEntity.getAbsorptionAmount() : livingEntity.getMaxAbsorption();
        float maxHealthRed = livingEntity.getMaxHealth();

        float scale = INDICATOR_SCALE * 2.f / 7.f;
        float numberHeightOffset = livingEntity.isPlayer() ? HEIGHT_OFFSET + PLAYER_HEIGHT_OFFSET : HEIGHT_OFFSET;
        float configOffset = (float) config.indicator.layout.positionY / 100.f;
        float entityHeight = livingEntity.getHeight() + numberHeightOffset + configOffset;

        float dx = MathHelper.sign(client.player.getX() - livingEntity.getX());
        float dy = MathHelper.sign(client.player.getY() - livingEntity.getY());
        float dz = MathHelper.sign(client.player.getZ() - livingEntity.getZ());

        float xOffset = TEXT_OFFSET * dx;
        float yOffset = TEXT_OFFSET * dy;
        float zOffset = TEXT_OFFSET * dz;

        matrixStack.push();
        matrixStack.translate(xOffset, yOffset, zOffset); // bar-text offset
        matrixStack.translate(0, entityHeight, 0);
        matrixStack.multiply(client.getEntityRenderDispatcher().getRotation());
        matrixStack.scale(scale, -scale, scale);

        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

        String formattedHealthRed = FormatUtil.formatHealthFloat(currentHealthRed);
        String formattedHealthYellow = FormatUtil.formatHealthFloat(currentHealthYellow);
        String formattedMaxHealthRed = FormatUtil.formatHealthFloat(maxHealthRed);

        String healthRedText = formattedHealthRed + " / " + formattedMaxHealthRed;
        String healthYellowText = " (+" + formattedHealthYellow + ")";

        boolean absorption = currentHealthYellow > 0;

        TextRenderer.drawEntityHealth(client, matrix4f, vertexConsumerProvider, healthRedText, healthYellowText, absorption);

        RenderSystem.disableBlend();
        matrixStack.pop();
    }
}