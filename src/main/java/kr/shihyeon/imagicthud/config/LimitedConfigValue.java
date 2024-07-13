package kr.shihyeon.imagicthud.config;

public class LimitedConfigValue {

    public static void setValueWithLimit(ImagictHudConfig config) {
        setHudLimit(config);
    }

    private static void setHudLimit(ImagictHudConfig config) {
        setHudComponentLimit(config);
        setHudHeadLimit(config);
        setHudLabelLimit(config);
        setHudCustomLabel(config);
        setHudLabelText(config);
        setHudLayout(config);
    }

    private static void setHudComponentLimit(ImagictHudConfig config) {

    }

    private static void setHudHeadLimit(ImagictHudConfig config) {

    }

    private static void setHudLabelLimit(ImagictHudConfig config) {
        if (config.labelBackgoundOpacity > 100) {
            config.labelBackgoundOpacity = 100;
        } else if (config.labelBackgoundOpacity < 0) {
            config.labelBackgoundOpacity = 0;
        }
    }

    private static void setHudCustomLabel(ImagictHudConfig config) {

    }

    private static void setHudLabelText(ImagictHudConfig config) {
        if (config.labelTextOpacity > 100) {
            config.labelTextOpacity = 100;
        } else if (config.labelTextOpacity < 0) {
            config.labelTextOpacity = 0;
        }
    }

    private static void setHudLayout(ImagictHudConfig config) {
        if (config.labelWidth > 150) {
            config.labelWidth = 150;
        } else if (config.labelWidth < 0) {
            config.labelWidth = 0;
        }

        if (config.labelTextLineSpacing > 5) {
            config.labelTextLineSpacing = 5;
        } else if (config.labelTextLineSpacing < -5) {
            config.labelTextLineSpacing = -5;
        }

        if (config.hudScale > 2.f) {
            config.hudScale = 2.f;
        } else if (config.hudScale < .5f) {
            config.hudScale = .5f;
        }

        if (config.xPosition > 50) {
            config.xPosition = 50;
        } else if (config.xPosition < 0) {
            config.xPosition = 0;
        }

        if (config.yPosition > 50) {
            config.yPosition = 50;
        } else if (config.yPosition < 0) {
            config.yPosition = 0;
        }

        if (config.offset > 50) {
            config.offset = 50;
        } else if (config.offset < 0) {
            config.offset = 0;
        }
    }
}
