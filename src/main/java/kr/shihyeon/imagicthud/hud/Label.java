package kr.shihyeon.imagicthud.hud;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.PlayerListEntry;

import java.util.List;

public class Label {
    private static ImagictHudConfig config = ImagictHudClient.CONFIG;

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
            if (config.enableLabelFrame) {
                ResourceGui.renderLabelFrame(context, x, lineY, width, height, frameColor);
            }
            TextGui.renderText(context, client, text, x + textCenteredX, lineY + lineSpacingCenter, textColor, shadow);
        }
    }
}
