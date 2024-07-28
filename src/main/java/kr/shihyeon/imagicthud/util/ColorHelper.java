package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;

public class ColorHelper {
    private static ImagictHudConfig config = ImagictHudClient.CONFIG;

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
}
