package kr.shihyeon.imagicthud.gui.render;

import kr.shihyeon.imagicthud.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import org.joml.Matrix4f;

public class TextRenderer {

    public static void drawText(GuiGraphics context, Minecraft client, String text, int x, int y, int color, boolean shadow) {
        if (client != null && client.font != null) {
            context.drawString(client.font, text, x, y, color, shadow);
        }
    }

    public static void drawEntityHealth(Minecraft client, Matrix4f matrix, MultiBufferSource vertexConsumer, String healthRed, String healthYellow, boolean absorption, boolean shadow) {
        if (client != null && client.font != null) {
            Component txt1 = Component.literal(healthRed);
            Component txt2 = Component.literal("");
            if (absorption) {
                txt2 = Component.literal(healthYellow);
            }
            float w1 = client.font.width(txt1);
            float w2 = client.font.width(txt2);
            float x1 = -(w1 + w2) / 2.f;
            float x2 = -(w1 + w2) / 2.f + w1;
            RenderUtil.drawTextMatrix(matrix, vertexConsumer, txt1, x1, -3.6f, 0xffffffff, shadow);
            RenderUtil.drawTextMatrix(matrix, vertexConsumer, txt2, x2, -3.6f, 0xffffff55, shadow);
        }
    }

    public static void drawEntityName(Minecraft client, Matrix4f matrix, MultiBufferSource vertexConsumer, String name, float y, boolean shadow) {
        if (client != null && client.font != null) {
            Component text = Component.literal(name);
            float width = client.font.width(text);
            float x = - width / 2.f;
            RenderUtil.drawTextMatrix(matrix, vertexConsumer, text, x, y -3.6f, 0xffffffff, shadow);
        }
    }
}
