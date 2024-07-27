package kr.shihyeon.imagicthud.gui.screen;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.enums.TextAlignMode;
import kr.shihyeon.imagicthud.util.ColorHelper;
import kr.shihyeon.imagicthud.util.LayoutUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

import java.util.List;

public class Hud {
    private static PlayerListEntry playerListEntry;
    private static ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static void renderHud(DrawContext context, MinecraftClient client) {
        if (!client.options.hudHidden && config.general.hud.enableHud && client.player != null) {
            float scale = config.hud.layout.hudScale;

            int posX = MathHelper.floor(LayoutUtil.getPosX(client) / scale);
            int posY = MathHelper.floor(LayoutUtil.getPosY(client) / scale);

            int width = config.hud.layout.labelWidth;
            int height = 13;
            int lineSpacing = config.hud.layout.labelLineSpacing;

            int frameColor = ColorHelper.getLabelFrameColor();
            int backgroundColor = ColorHelper.getLabelBackgroundColor();
            int textColor = ColorHelper.getLabelTextColor();
            boolean shadow = config.hud.text.enableTextShadows;
            boolean center = config.hud.text.textAlignMode == TextAlignMode.CENTER;

            if (playerListEntry == null) {
                playerListEntry = client.player.networkHandler.getPlayerListEntry(client.player.getUuid());
            }

            MatrixStack matrixStack = context.getMatrices();
            matrixStack.push();
            matrixStack.scale(scale, scale, 1.0f);

            if (config.hud.display.enableHead) {
                switch (config.hud.head.headRenderMode) {
                    case BOLD -> ResourceGui.renderBoldHead(context, playerListEntry, posX, posY);
                    case FLAT -> ResourceGui.renderHead(context, playerListEntry, posX, posY);
                    case null, default -> ResourceGui.renderBoldHead(context, playerListEntry, posX, posY);
                }
                posX = LayoutUtil.getLabelPosX(client);
            }

            renderLabel(context, client, playerListEntry, posX, posY, width, height, lineSpacing, frameColor, backgroundColor, textColor, shadow, center);

            matrixStack.pop();
        }
    }

    public static void renderLabel(DrawContext context, MinecraftClient client, PlayerListEntry playerListEntry,
                                   int x, int y, int width, int height, int lineSpacing,
                                   int frameColor, int backgroundColor, int textColor,
                                   boolean shadow, boolean center) {
        List<String> textLines = TextGui.getStrings(client, config, playerListEntry);

        for (int i = 0; i < textLines.size(); i++) {
            String text = textLines.get(i);
            int lineSpacingOffset = 8;
            int lineSpacingCenter = lineSpacingOffset / 2 - 1;
            int textCenteredX = center ? (width - client.textRenderer.getWidth(text))/2 : 4;
            int lineY = y + i * (client.textRenderer.fontHeight + (lineSpacing + lineSpacingOffset));
            ResourceGui.renderLabelBackground(context, x, lineY, width, height, backgroundColor);
            if (config.hud.label.enableLabelFrame) {
                ResourceGui.renderLabelFrame(context, x, lineY, width, height, frameColor);
            }
            TextGui.renderText(context, client, text, x + textCenteredX, lineY + lineSpacingCenter, textColor, shadow);
        }
    }
}
