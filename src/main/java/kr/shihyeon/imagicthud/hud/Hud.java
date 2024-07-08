package kr.shihyeon.imagicthud.hud;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.util.ColorHelper;
import kr.shihyeon.imagicthud.util.LayoutUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;

public class Hud {
    private static PlayerListEntry playerListEntry;
    private static ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static void renderHud(DrawContext context, MinecraftClient client) {
        if (!client.options.hudHidden && config.enableHud && client.player != null) {
            int posX = LayoutUtil.getPosX(client);
            int posY = LayoutUtil.getPosY(client);

            int width = config.labelWidth;
            int height = 13;
            int lineSpacing = config.lebelTextLineSpacing;

            float scale = config.scale;

            int frameColor = ColorHelper.getLabelFrameColor();
            int backgroundColor = ColorHelper.getLabelBackgroundColor();
            int textColor = ColorHelper.getLabelTextColor();
            boolean shadow = config.enableLabelTextShadows;
            boolean center = config.enableLabelCenteredText;

            if (playerListEntry == null) {
                playerListEntry = client.player.networkHandler.getPlayerListEntry(client.player.getUuid());
            }

            MatrixStack matrixStack = context.getMatrices();
            matrixStack.push();
            matrixStack.scale(scale, scale, 1.0f);

            if (config.enableHeadHud) {
                ResourceGui.renderHead(context, playerListEntry, posX, posY);
                posX = LayoutUtil.getLabelPosX(client);
            }

            Label.renderLabel(context, client, playerListEntry, posX, posY, width, height, lineSpacing, frameColor, backgroundColor, textColor, shadow, center);

            matrixStack.pop();
        }
    }
}
