package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;

public class ColorHelper {
    private static ImagictHudConfig config = ImagictHudClient.getConfig();

    public static int getLabelBackgroundColor() {
        int rgb = config.hud.label.labelBackgroundColor.getRGB() & 0xFFFFFF;
        int opacity = config.hud.label.labelBackgroundOpacity * 255/100;
        return ((opacity & 0xFF) << 24) | rgb;
    }

    public static int getLabelFrameColor() {
        int rgb = config.hud.label.labelFrameColor.getRGB() & 0xFFFFFF;
        int opacity = 100 * 255/100;
        return ((opacity & 0xFF) << 24) | rgb;
    }

    public static int getLabelTextColor() {
        int rgb = config.hud.text.textColor.getRGB() & 0xFFFFFF;
        int opacity = config.hud.text.textOpacity * 255/100;
        return ((opacity & 0xFF) << 24) | rgb;
    }

    public static int darkenColor(int color, float factor) {
        int alpha = (color >> 24) & 0xff;
        int red = (color >> 16) & 0xff;
        int green = (color >> 8) & 0xff;
        int blue = color & 0xff;

        red = Math.max((int) (red * factor), 0);
        green = Math.max((int) (green * factor), 0);
        blue = Math.max((int) (blue * factor), 0);

        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }
}
