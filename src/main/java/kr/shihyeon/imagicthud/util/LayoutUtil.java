package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import net.minecraft.client.MinecraftClient;

public class LayoutUtil {
    private static ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static int getPosX(MinecraftClient client) {
        double guiScale = client.getWindow().getScaleFactor();
        int maxPosX = client.getWindow().getScaledWidth();
        int posX = Math.min(Math.round(config.offset / (float) guiScale + (config.xPosition + 0) + 2), maxPosX);
        return posX;
    }

    public static int getLabelPosX(MinecraftClient client) {
        double guiScale = client.getWindow().getScaleFactor();
        int maxPosX = client.getWindow().getScaledWidth();
        int headHudOffsetX = config.enableHeadHud ? 52 : 0;
        int posX = Math.min(Math.round(config.offset / (float) guiScale + (config.xPosition + headHudOffsetX) + 2), maxPosX);
        return posX;
    }

    public static int getPosY(MinecraftClient client) {
        double guiScale = client.getWindow().getScaleFactor();
        int maxPosY = client.getWindow().getScaledHeight();
        int posY = Math.min(Math.round(config.offset / (float) guiScale + (config.yPosition + 0) + 2), maxPosY);
        return posY;
    }
}
