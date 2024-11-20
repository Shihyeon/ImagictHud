package kr.shihyeon.imagicthud.gui.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexConsumer;
import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.util.ColorHelper;
import kr.shihyeon.imagicthud.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.renderer.CoreShaders;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ARGB;
import org.joml.Matrix4f;

import java.util.Optional;
import java.util.function.Function;

public class ResourceRenderer {

    protected static Optional<ResourceLocation> blendedHeadTexture = Optional.empty();

    public static ResourceLocation getBlendedLocation(ResourceLocation textureLocation) {
        return blendedHeadTexture
                .map(location -> ResourceLocation.fromNamespaceAndPath(ImagictHudClient.MODID, textureLocation.getPath()))
                .orElse(textureLocation);
    }

    public static void renderLabelFrame(GuiGraphics context, int x, int y, int width, int height, int color) {
        RenderUtil.fillContext(context, x + 1, y, x + width - 1, y + 1, color);
        RenderUtil.fillContext(context, x, y + 1, x + 1, y + height - 1, color);
        RenderUtil.fillContext(context, x + width, y + 1, x + width - 1, y + height - 1, color);
        RenderUtil.fillContext(context, x + 1, y + height, x + width - 1, y + height - 1, color);
    }

    public static void renderLabelBackground(GuiGraphics context, int x, int y, int width, int height, int color) {
        RenderUtil.fillContext(context, x + 1, y, x + width - 1, y + 1, color);
        RenderUtil.fillContext(context, x, y + 1, x + width, y + height - 1, color);
        RenderUtil.fillContext(context, x + 1, y + height, x + width - 1, y + height - 1, color);
    }

    public static void renderHead(GuiGraphics context, PlayerInfo playerListEntry, int x, int y) {
        ResourceLocation skinLocation = playerListEntry.getSkin().texture();

        int offset = 6;
        int initPosX = offset * 8;
        int initPosY = initPosX;
        float u = offset * 8.f;
        float v = offset * 8.f;
        float uh = u * 5;
        int regionSize = offset * 8;
        int textureSize = regionSize * 8;

        float opacity = 1.0f;
        int color = ARGB.white(opacity);

        if (blendedHeadTexture.isPresent() && blendedHeadTexture.get().equals(skinLocation)) {
            RenderSystem.enableBlend();
            context.blit(RenderType::guiTextured, getBlendedLocation(skinLocation), x, y, 0, 0, initPosX, initPosY, regionSize, regionSize, regionSize, regionSize, color);
            RenderSystem.disableBlend();
        } else {
            RenderSystem.enableBlend();
            context.blit(RenderType::guiTextured, skinLocation, x, y, u, v, initPosX, initPosY, regionSize, regionSize, textureSize, textureSize, color);
            context.blit(RenderType::guiTextured, skinLocation, x, y, uh, v, initPosX, initPosY, regionSize, regionSize, textureSize, textureSize, color);
            RenderSystem.disableBlend();
        }
    }

    public static void renderBoldHead(GuiGraphics context, PlayerInfo playerListEntry, int x, int y) {
        ResourceLocation skinLocation = playerListEntry.getSkin().texture();

        int offset = 6;
        int initPosX = offset * 8;
        int initPosY = initPosX;
        int initHeadPosX = initPosX * 14 / 16;
        int initHeadPosY = initPosY * 14 / 16;
        float u = offset * 8.f;
        float v = offset * 8.f;
        float uh = u * 5;
        int regionSize = offset * 8;
        int textureSize = regionSize * 8;

        float opacity = 1.0f;
        int color = ARGB.white(opacity);

        if (blendedHeadTexture.isPresent() && blendedHeadTexture.get().equals(skinLocation)) {
            RenderSystem.enableBlend();
            context.blit(RenderType::guiTextured, getBlendedLocation(skinLocation), x, y, initPosX, initPosY, 0, 0, regionSize, regionSize, regionSize, regionSize, color);
            RenderSystem.disableBlend();
        } else {
            RenderSystem.enableBlend();
            context.blit(RenderType::guiTextured, skinLocation, x + offset / 2, y + offset / 2, u, v, initHeadPosX, initHeadPosY, regionSize, regionSize, textureSize, textureSize, color);
            context.blit(RenderType::guiTextured, skinLocation, x, y, uh, v, initPosX, initPosY, regionSize, regionSize, textureSize, textureSize, color);
            RenderSystem.disableBlend();
        }
    }

    public static void drawEntityBar(Matrix4f matrix, VertexConsumer vertexConsumer, float percentageHealthRed, float percentageHealthYellow) {
        RenderSystem.enableDepthTest();
        RenderSystem.setShader(CoreShaders.POSITION_COLOR);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        drawEntityFrameBar(matrix, vertexConsumer);
        drawEntityBackgroundBar(matrix, vertexConsumer, percentageHealthRed, percentageHealthYellow);
        drawEntityHealthBar(matrix, vertexConsumer, percentageHealthRed, percentageHealthYellow);
    }

    private static void drawEntityFrameBar(Matrix4f matrix, VertexConsumer vertexConsumer) {
        float width = 42.f;
        float initPosX = width / 2.f;

        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + 1.f, -2.f, initPosX - 1.f, -1.f, 0xff333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, -1.f, -initPosX + 1.f, 1.f, 0xff333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, initPosX - 1.f, -1.f, initPosX, 1.f, 0xff222222);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + 1.f, 1.f, initPosX - 1.f, 2.f, 0xff222222);
    }

    private static void drawEntityBackgroundBar(Matrix4f matrix, VertexConsumer vertexConsumer, float percentageHealthRed, float percentageHealthYellow) {
        float width = 40.f;
        float initPosX = width / 2.f;
        float healthRed = width * percentageHealthRed;
        float healthYellow = width * percentageHealthYellow;

        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + healthRed + healthYellow, -1.f, initPosX, 0, 0x80333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + healthRed + healthYellow, 0, initPosX, 1.f, 0x80222222);
    }

    private static void drawEntityHealthBar(Matrix4f matrix, VertexConsumer vertexConsumer, float percentageHealthRed, float percentageHealthYellow) {
        float width = 40.f;
        float initPosX = width / 2.f;
        float healthRed = width * percentageHealthRed;
        float healthYellow = width * percentageHealthYellow;

        // Health
        int healthColor = 0xffaa0000;
        int darkerHealthColor = ColorHelper.darkenColor(healthColor, 0.8f);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, -1.f, -initPosX + healthRed, 0, healthColor);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, 0, -initPosX + healthRed, 1.f, darkerHealthColor);

        // Absorption
        int absorptionColor = 0xffcfcf45;
        int darkerAbsorptionColor = ColorHelper.darkenColor(absorptionColor, 0.8f);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + healthRed, -1.f, -initPosX + healthRed + healthYellow, 0, absorptionColor);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + healthRed, 0, -initPosX + healthRed + healthYellow, 1.f, darkerAbsorptionColor);
    }

    public static void drawEntityNameBackground(Matrix4f matrix, VertexConsumer vertexConsumer, String name, float y, Minecraft client) {
        float width = client.font.width(name);
        float height = client.font.lineHeight;
        float initPosX = width / 2.f;
        float initPosY = height / 2.f;

        RenderSystem.enableDepthTest();
        RenderSystem.setShader(CoreShaders.POSITION_COLOR);

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, y - initPosY - 1.f, initPosX, y - initPosY, 0x80333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX - 1.f, y - initPosY, initPosX + 1.f, y + initPosY, 0x80333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, y + initPosY, initPosX, y + initPosY + 1.f, 0x80333333);
    }
}
