package kr.shihyeon.imagicthud.config;

public class LimitedConfigValue {

    public static void setValueWithLimit(ImagictHudConfig config) {
        setGeneralLimit(config);
        setHudLimit(config);
        setIndicatorLimit(config);
    }

    // General
    private static void setGeneralLimit(ImagictHudConfig config) {
        setGeneralHudLimit(config);
        setGeneralIndicatorLimit(config);
    }

    private static void setGeneralHudLimit(ImagictHudConfig config) {

    }

    private static void setGeneralIndicatorLimit(ImagictHudConfig config) {

    }

    // Hud
    private static void setHudLimit(ImagictHudConfig config) {
        setHudDisplayLimit(config);
        setHudHeadLimit(config);
        setHudLabelLimit(config);
        setHudText(config);
        setHudLayout(config);
    }

    private static void setHudDisplayLimit(ImagictHudConfig config) {

    }

    private static void setHudHeadLimit(ImagictHudConfig config) {

    }

    private static void setHudLabelLimit(ImagictHudConfig config) {
        config.hud.label.labelBackgoundOpacity = applyLimit(config.hud.label.labelBackgoundOpacity, 0, 100);
    }

    private static void setHudText(ImagictHudConfig config) {
        config.hud.text.textOpacity = applyLimit(config.hud.text.textOpacity, 0, 100);
    }

    private static void setHudLayout(ImagictHudConfig config) {
        config.hud.layout.labelWidth = applyLimit(config.hud.layout.labelWidth, 0, 150);
        config.hud.layout.labelLineSpacing = applyLimit(config.hud.layout.labelLineSpacing, -5, 5);
        config.hud.layout.hudScale = applyLimit(config.hud.layout.hudScale, .5f, 2.f);
        config.hud.layout.positionX = applyLimit(config.hud.layout.positionX, 0, 50);
        config.hud.layout.positionY = applyLimit(config.hud.layout.positionY, 0, 50);
        config.hud.layout.offset = applyLimit(config.hud.layout.offset, 0, 50);
    }

    // Indicator
    private static void setIndicatorLimit(ImagictHudConfig config) {
        setIndicatorDisplayLimit(config);
        setIndicatorEntitiesLimit(config);
    }

    private static void setIndicatorDisplayLimit(ImagictHudConfig config) {
        config.indicator.display.duration = applyLimit(config.indicator.display.duration, 0, 120);
        config.indicator.display.reach = applyLimit(config.indicator.display.reach, 3, 50);
    }

    private static void setIndicatorEntitiesLimit(ImagictHudConfig config) {

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
