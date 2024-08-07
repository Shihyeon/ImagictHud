package kr.shihyeon.imagicthud.gui.render;

import com.mojang.blaze3d.systems.RenderSystem;
import kr.shihyeon.imagicthud.ImagictHud;
import kr.shihyeon.imagicthud.util.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

import java.util.HashSet;
import java.util.Set;

public class ResourceRenderer {

    protected static final Set<Identifier> blendedHeadTextures = new HashSet<>();

    public static Identifier getBlendedLocation(Identifier textureLocation) {
        return Identifier.of(ImagictHud.MODID, textureLocation.getPath());
    }

    public static void renderLabelFrame(DrawContext context, int x, int y, int width, int height, int color) {
        RenderUtil.fillContext(context, x+1, y, x+width-1, y+1, color);
        RenderUtil.fillContext(context, x, y+1, x+1, y+height-1, color);
        RenderUtil.fillContext(context, x+width, y+1, x+width-1, y+height-1, color);
        RenderUtil.fillContext(context, x+1, y+height, x+width-1, y+height-1, color);
    }

    public static void renderLabelBackground(DrawContext context, int x, int y, int width, int height, int color) {
        RenderUtil.fillContext(context, x+1, y, x+width-1, y+1, color);
        RenderUtil.fillContext(context, x, y+1, x+width, y+height-1, color);
        RenderUtil.fillContext(context, x+1, y+height, x+width-1, y+height-1, color);
    }

    public static void renderHead(DrawContext context, PlayerListEntry playerListEntry, int x, int y) {
        Identifier skinLocation = playerListEntry.getSkinTextures().texture();

        int offset = 6;
        int initPosX = offset * 8;
        int initPosY = initPosX;
        float u = offset * 8.f;
        float v = offset * 8.f;
        float uh = u * 5;
        int regionSize = offset * 8;
        int textureSize = regionSize * 8;

        if (blendedHeadTextures.contains(skinLocation)) {
            context.drawTexture(getBlendedLocation(skinLocation), x, y, initPosX, initPosY, 0, 0, regionSize, regionSize, regionSize, regionSize);
        } else {
            context.drawTexture(skinLocation, x, y, initPosX, initPosY, u, v, regionSize, regionSize, textureSize, textureSize);
            context.drawTexture(skinLocation, x, y, initPosX, initPosY, uh, v, regionSize, regionSize, textureSize, textureSize);
        }
    }

    public static void renderBoldHead(DrawContext context, PlayerListEntry playerListEntry, int x, int y) {
        Identifier skinLocation = playerListEntry.getSkinTextures().texture();

        int offset = 6;
        int initPosX = offset * 8;
        int initPosY = initPosX;
        int initHeadPosX = initPosX * 14/16;
        int initHeadPosY = initPosY * 14/16;
        float u = offset * 8.f;
        float v = offset * 8.f;
        float uh = u * 5;
        int regionSize = offset * 8;
        int textureSize = regionSize * 8;

        if (blendedHeadTextures.contains(skinLocation)) {
            context.drawTexture(getBlendedLocation(skinLocation), x, y, initPosX, initPosY, 0, 0, regionSize, regionSize, regionSize, regionSize);
        } else {
            context.drawTexture(skinLocation, x + offset/2, y + offset/2, initHeadPosX, initHeadPosY, u, v, regionSize, regionSize, textureSize, textureSize);
            context.drawTexture(skinLocation, x, y, initPosX, initPosY, uh, v, regionSize, regionSize, textureSize, textureSize);
        }
    }

    public static void drawEntityBar(Matrix4f matrix, VertexConsumer vertexConsumer, float percentageHealthRed, float percentageHealthYellow) {
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.enableDepthTest();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        drawEntityFrameBar(matrix, vertexConsumer);
        drawEntityBackgroundBar(matrix, vertexConsumer, percentageHealthRed, percentageHealthYellow);
        drawEntityHealthBar(matrix, vertexConsumer, percentageHealthRed, percentageHealthYellow);
    }

    private static void drawEntityFrameBar(Matrix4f matrix, VertexConsumer vertexConsumer) {
        float width = 42.f;
        float initPosX = width/2.f;

        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + 1.f, - 2.f, initPosX - 1.f, -1.f, 0xff333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, - 1.f, -initPosX + 1.f, 1.f, 0xff333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, initPosX - 1.f, - 1.f, initPosX, 1.f, 0xff222222);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX + 1.f, 1.f, initPosX - 1.f, 2.f, 0xff222222);
    }

    private static void drawEntityBackgroundBar(Matrix4f matrix, VertexConsumer vertexConsumer, float percentageHealthRed, float percentageHealthYellow) {
        float width = 40.f;
        float initPosX = width/2.f;
        float healthRed = width * percentageHealthRed;
        float healthYellow = width * percentageHealthYellow;

        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX +healthRed +healthYellow, -1.f, initPosX, 0, 0x80333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX +healthRed +healthYellow, 0, initPosX, 1.f, 0x80222222);
    }

    private static void drawEntityHealthBar(Matrix4f matrix, VertexConsumer vertexConsumer, float percentageHealthRed, float percentageHealthYellow) {
        float width = 40.f;
        float initPosX = width/2.f;
        float healthRed = width * percentageHealthRed;
        float healthYellow = width * percentageHealthYellow;

        // Health
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, -1.f, -initPosX +healthRed, 0, 0xffaa0000);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, 0, -initPosX +healthRed, 1.f, 0xff880000);

        // Absorption
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX +healthRed, -1.f, -initPosX +healthRed +healthYellow, 0, 0xffcfcf45);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX +healthRed, 0, -initPosX +healthRed +healthYellow, 1.f, 0xffbfbf40); //dede4a
    }

    public static void drawEntityNameBackground(Matrix4f matrix, VertexConsumer vertexConsumer, String name, float y, MinecraftClient client) {
        float width = client.textRenderer.getWidth(name);
        float height = client.textRenderer.fontHeight;
        float initPosX = width / 2.f;
        float initPosY = height / 2.f;

        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
        RenderSystem.enableDepthTest();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, y -initPosY -1.f, initPosX, y -initPosY, 0x80333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX -1.f, y -initPosY, initPosX +1.f, y +initPosY, 0x80333333);
        RenderUtil.fillMatrix(matrix, vertexConsumer, -initPosX, y +initPosY, initPosX, y +initPosY +1.f, 0x80333333);
    }
}
