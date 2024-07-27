package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.MinecraftClient;

public class LayoutUtil {
    private static ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static int getPosX(MinecraftClient client) {
        double guiScale = client.getWindow().getScaleFactor();
        int maxPosX = client.getWindow().getScaledWidth();
        int posX = Math.min(Math.round(config.hud.layout.offset / (float) guiScale + (config.hud.layout.positionX + 0) + 2), maxPosX);
        return posX;
    }

    public static int getLabelPosX(MinecraftClient client) {
        double guiScale = client.getWindow().getScaleFactor();
        int maxPosX = client.getWindow().getScaledWidth();
        int headHudOffsetX = config.hud.display.enableHead ? 52 : 0;
        int posX = Math.min(Math.round(config.hud.layout.offset / (float) guiScale + (config.hud.layout.positionX + headHudOffsetX) + 2), maxPosX);
        return posX;
    }

    public static int getPosY(MinecraftClient client) {
        double guiScale = client.getWindow().getScaleFactor();
        int maxPosY = client.getWindow().getScaledHeight();
        int posY = Math.min(Math.round(config.hud.layout.offset / (float) guiScale + (config.hud.layout.positionY + 0) + 2), maxPosY);
        return posY;
    }
}
