package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.Minecraft;

public class LayoutUtil {
    private static ImagictHudConfig config = ImagictHudClient.getConfig();

    public static int getPosX(Minecraft client) {
        double guiScale = client.getWindow().getGuiScale();
        int maxPosX = client.getWindow().getGuiScaledWidth();
        int posX = Math.min(Math.round(config.hud.layout.offset / (float) guiScale + (config.hud.layout.positionX + 0) + 2), maxPosX);
        return posX;
    }

    public static int getLabelPosX(Minecraft client) {
        double guiScale = client.getWindow().getGuiScale();
        int maxPosX = client.getWindow().getGuiScaledWidth();
        int headHudOffsetX = config.hud.display.enableHead ? 52 : 0;
        int posX = Math.min(Math.round(config.hud.layout.offset / (float) guiScale + (config.hud.layout.positionX + headHudOffsetX) + 2), maxPosX);
        return posX;
    }

    public static int getPosY(Minecraft client) {
        double guiScale = client.getWindow().getGuiScale();
        int maxPosY = client.getWindow().getGuiScaledHeight();
        int posY = Math.min(Math.round(config.hud.layout.offset / (float) guiScale + (config.hud.layout.positionY + 0) + 2), maxPosY);
        return posY;
    }
}
