package kr.shihyeon.imagicthud.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.categories.hud.groups.enums.TextAlignMode;
import kr.shihyeon.imagicthud.gui.render.ResourceRenderer;
import kr.shihyeon.imagicthud.gui.render.TextRenderer;
import kr.shihyeon.imagicthud.util.ColorHelper;
import kr.shihyeon.imagicthud.util.LayoutUtil;
import kr.shihyeon.imagicthud.util.TextUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.util.Mth;

import java.util.List;

public class Hud {
    private static PlayerInfo playerListEntry;

    public static void renderHud(GuiGraphics context, Minecraft client, ImagictHudConfig config) {
        if (!client.options.hideGui && config.hud.general.enableHud && client.player != null) {
            float scale = config.hud.layout.hudScale == 0 ? 1.f : (float) config.hud.layout.hudScale / 4.f;

            int posX = Mth.floor(LayoutUtil.getPosX(client) / scale);
            int posY = Mth.floor(LayoutUtil.getPosY(client) / scale);

            int width = config.hud.layout.labelWidth;
            int height = 13;
            int lineSpacing = config.hud.layout.labelLineSpacing;

            int frameColor = ColorHelper.getLabelFrameColor();
            int backgroundColor = ColorHelper.getLabelBackgroundColor();
            int textColor = ColorHelper.getLabelTextColor();
            boolean shadow = config.hud.text.enableTextShadows;
            boolean center = config.hud.text.textAlignMode == TextAlignMode.CENTER;

            if (playerListEntry == null) {
                playerListEntry = client.player.connection.getPlayerInfo(client.player.getUUID());
            }

            PoseStack matrixStack = context.pose();
            matrixStack.pushPose();
            matrixStack.scale(scale, scale, 1.0f);

            if (config.hud.display.enableHead) {
                switch (config.hud.head.headRenderMode) {
                    case BOLD -> ResourceRenderer.renderBoldHead(context, playerListEntry, posX, posY);
                    case FLAT -> ResourceRenderer.renderHead(context, playerListEntry, posX, posY);
                }
                posX = LayoutUtil.getLabelPosX(client);
            }

            renderLabel(context, client, config, playerListEntry, posX, posY, width, height, lineSpacing, frameColor, backgroundColor, textColor, shadow, center);

            matrixStack.popPose();
        }
    }

    public static void renderLabel(GuiGraphics context, Minecraft client, ImagictHudConfig config, PlayerInfo playerListEntry,
                                   int x, int y, int width, int height, int lineSpacing,
                                   int frameColor, int backgroundColor, int textColor,
                                   boolean shadow, boolean center) {

        List<String> textLines = TextUtil.getLabelTexts(client, config, playerListEntry);

        for (int i = 0; i < textLines.size(); i++) {
            String text = textLines.get(i);
            int lineSpacingOffset = 8;
            int lineSpacingCenter = lineSpacingOffset / 2 - 1;
            int textCenteredX = center ? (width - client.font.width(text))/2 : 4;
            int lineY = y + i * (client.font.lineHeight + (lineSpacing + lineSpacingOffset));
            ResourceRenderer.renderLabelBackground(context, x, lineY, width, height, backgroundColor);
            if (config.hud.label.enableLabelFrame) {
                ResourceRenderer.renderLabelFrame(context, x, lineY, width, height, frameColor);
            }
            TextRenderer.drawText(context, client, text, x + textCenteredX, lineY + lineSpacingCenter, textColor, shadow);
        }
    }
}
