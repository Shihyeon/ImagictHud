package kr.shihyeon.imagicthud.config;

public class LimitedConfigValue {

    public static void setValueWithLimit(ImagictHudConfig config) {
        setHudLimit(config);
        setIndicatorLimit(config);
    }

    // Hud
    private static void setHudLimit(ImagictHudConfig config) {
        setHudGeneralLimit(config);
        setHudDisplayLimit(config);
        setHudHeadLimit(config);
        setHudLabelLimit(config);
        setHudText(config);
        setHudLayout(config);
    }

    private static void setHudGeneralLimit(ImagictHudConfig config) {}

    private static void setHudDisplayLimit(ImagictHudConfig config) {}

    private static void setHudHeadLimit(ImagictHudConfig config) {}

    private static void setHudLabelLimit(ImagictHudConfig config) {
        config.hud.label.labelBackgroundOpacity = applyLimit(config.hud.label.labelBackgroundOpacity, 0, 100);
    }

    private static void setHudText(ImagictHudConfig config) {
        config.hud.text.textOpacity = applyLimit(config.hud.text.textOpacity, 0, 100);
    }

    private static void setHudLayout(ImagictHudConfig config) {
        config.hud.layout.labelWidth = applyLimit(config.hud.layout.labelWidth, 0, 150);
        config.hud.layout.labelLineSpacing = applyLimit(config.hud.layout.labelLineSpacing, -5, 5);
        config.hud.layout.hudScale = applyLimit(config.hud.layout.hudScale, 0, 8);
        config.hud.layout.positionX = applyLimit(config.hud.layout.positionX, 0, 50);
        config.hud.layout.positionY = applyLimit(config.hud.layout.positionY, 0, 50);
        config.hud.layout.offset = applyLimit(config.hud.layout.offset, 0, 50);
    }

    // Indicator
    private static void setIndicatorLimit(ImagictHudConfig config) {
        setIndicatorGeneralLimit(config);
        setIndicatorDisplayLimit(config);
        setIndicatorEntitiesLimit(config);
        setIndicatorLayoutLimit(config);
    }

    private static void setIndicatorGeneralLimit(ImagictHudConfig config) {}

    private static void setIndicatorDisplayLimit(ImagictHudConfig config) {
        config.indicator.display.duration = applyLimit(config.indicator.display.duration, 0, 120);
        config.indicator.display.reach = applyLimit(config.indicator.display.reach, 3, 50);
    }

    private static void setIndicatorEntitiesLimit(ImagictHudConfig config) {}

    private static void setIndicatorLayoutLimit(ImagictHudConfig config) {
        config.indicator.layout.positionY = applyLimit(config.indicator.layout.positionY, -15, 15);
    }

    // Util
    private static float applyLimit(float value, float min, float max) {
        if (value > max) {
            return max;
        } else if (value < min) {
            return min;
        }
        return value;
    }

    private static int applyLimit(int value, int min, int max) {
        if (value > max) {
            return max;
        } else if (value < min) {
            return min;
        }
        return value;
    }
}
