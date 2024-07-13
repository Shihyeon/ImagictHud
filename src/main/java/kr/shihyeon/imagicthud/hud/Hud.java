package kr.shihyeon.imagicthud.hud;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.enums.HeadRenderMode;
import kr.shihyeon.imagicthud.config.enums.LabelTextAlignMode;
import kr.shihyeon.imagicthud.util.ColorHelper;
import kr.shihyeon.imagicthud.util.LayoutUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class Hud {
    private static PlayerListEntry playerListEntry;
    private static ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static void renderHud(DrawContext context, MinecraftClient client) {
        if (!client.options.hudHidden && config.enableHud && client.player != null) {
            float scale = config.hudScale;

            int posX = MathHelper.floor(LayoutUtil.getPosX(client) / scale);
            int posY = MathHelper.floor(LayoutUtil.getPosY(client) / scale);

            int width = config.labelWidth;
            int height = 13;
            int lineSpacing = config.labelTextLineSpacing;

            int frameColor = ColorHelper.getLabelFrameColor();
            int backgroundColor = ColorHelper.getLabelBackgroundColor();
            int textColor = ColorHelper.getLabelTextColor();
            boolean shadow = config.enableLabelTextShadows;
            boolean center = config.labelTextAlignMode == LabelTextAlignMode.CENTER;

            if (playerListEntry == null) {
                playerListEntry = client.player.networkHandler.getPlayerListEntry(client.player.getUuid());
            }

            MatrixStack matrixStack = context.getMatrices();
            matrixStack.push();
            matrixStack.scale(scale, scale, 1.0f);

            if (config.enableHeadHud) {
                if (config.headRenderMode == HeadRenderMode.BOLD) {
                    ResourceGui.renderBoldHead(context, playerListEntry, posX, posY);
                } else if (config.headRenderMode == HeadRenderMode.FLAT) {
                    ResourceGui.renderHead(context, playerListEntry, posX, posY);
                }
                posX = LayoutUtil.getLabelPosX(client);
            }

            Label.renderLabel(context, client, playerListEntry, posX, posY, width, height, lineSpacing, frameColor, backgroundColor, textColor, shadow, center);

            matrixStack.pop();
        }
    }
}
