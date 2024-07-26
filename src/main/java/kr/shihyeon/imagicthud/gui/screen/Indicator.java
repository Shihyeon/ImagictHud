package kr.shihyeon.imagicthud.gui.screen;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.util.EntityTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.joml.Matrix4f;

public class Indicator {

    public static void renderIndicator(LivingEntity livingEntity, float yaw, float tickDelta,
                                       MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                                       int light, MinecraftClient client) {

        if (ImagictHudClient.CONFIG.enableIndicator && !EntityTracker.isInvalid(livingEntity)) {
            if (EntityTracker.isInUUIDS(livingEntity)) {
                Indicator.renderBar(livingEntity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light, client);
            }
        }
    }

    private static void renderBar(LivingEntity livingEntity, float yaw, float tickDelta,
                                  MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider,
                                  int light, MinecraftClient client) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexConsumer;

        float currentHealthRed = livingEntity.getHealth();
        float currentHealthYellow = livingEntity.getMaxAbsorption();
        float maxHealthRed = livingEntity.getMaxHealth();
        float maxHealthYellow = livingEntity.getMaxAbsorption();
        float totalMaxHealth = maxHealthRed + maxHealthYellow;
        float percentageHealthRed = currentHealthRed / totalMaxHealth;
        float percentageHealthYellow = currentHealthYellow / totalMaxHealth;

        matrixStack.push();
        vertexConsumer = tessellator.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
        matrixStack.translate(0, livingEntity.getHeight() + 0.5f, 0);
        matrixStack.multiply(client.getEntityRenderDispatcher().getRotation());
        matrixStack.scale(-0.025f, 0.025f, 0.025f);
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

        ResourceGui.drawBar(matrix4f, vertexConsumer, percentageHealthRed, percentageHealthYellow);

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
}