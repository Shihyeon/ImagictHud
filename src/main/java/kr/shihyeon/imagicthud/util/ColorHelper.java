package kr.shihyeon.imagicthud.util;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;

public class ColorHelper {
    private static final ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static int getLabelBackgroundColor() {
        int rgb = config.labelBackgroundColor.getRGB() & 0xFFFFFF;
        int opacity = config.labelBackgoundOpacity * 255/100;
        return ((opacity & 0xFF) << 24) | rgb;
    }

    public static int getLabelFrameColor() {
        int rgb = config.labelFrameColor.getRGB() & 0xFFFFFF;
        int opacity = 100 * 255/100;
        return ((opacity & 0xFF) << 24) | rgb;
    }

    public static int getLabelTextColor() {
        int rgb = config.labelTextColor.getRGB() & 0xFFFFFF;
        int opacity = config.labelTextOpacity * 255/100;
        return ((opacity & 0xFF) << 24) | rgb;
    }
}
