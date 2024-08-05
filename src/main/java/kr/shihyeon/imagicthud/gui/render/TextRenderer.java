package kr.shihyeon.imagicthud.gui.render;

import kr.shihyeon.imagicthud.util.RenderUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.text.Text;
import org.joml.Matrix4f;

public class TextRenderer {

    public static void drawText(DrawContext context, MinecraftClient client, String text, int x, int y, int color, boolean shadow) {
        if (client != null && client.textRenderer != null) {
            context.drawText(client.textRenderer, text, x, y, color, shadow);
        }
    }

    public static void drawEntityHealth(MinecraftClient client, Matrix4f matrix, VertexConsumerProvider vertexConsumer, String healthRed, String healthYellow, boolean absorption, boolean shadow) {
        if (client != null && client.textRenderer != null) {
            Text txt1 = Text.literal(healthRed);
            Text txt2 = Text.literal("");
            if (absorption) {
                txt2 = Text.literal(healthYellow);
            }
            float w1 = client.textRenderer.getWidth(txt1);
            float w2 = client.textRenderer.getWidth(txt2);
            float x1 = -(w1 + w2) / 2.f;
            float x2 = -(w1 + w2) / 2.f + w1;
            RenderUtil.drawText(matrix, vertexConsumer, txt1, x1, -3.6f, 0xffffffff, shadow);
            RenderUtil.drawText(matrix, vertexConsumer, txt2, x2, -3.6f, 0xffffff55, shadow);
        }
    }

    public static void drawEntityName(MinecraftClient client, Matrix4f matrix, VertexConsumerProvider vertexConsumer, String name, boolean shadow) {
        if (client != null && client.textRenderer != null) {
            Text text = Text.literal(name);
            float width = client.textRenderer.getWidth(text);
            float x = - width / 2.f;
            RenderUtil.drawText(matrix, vertexConsumer, text, x, -3.6f, 0xffffffff, shadow);
        }
    }
}
