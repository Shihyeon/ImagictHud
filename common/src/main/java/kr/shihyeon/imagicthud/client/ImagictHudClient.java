package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.LimitedConfigValue;
import kr.shihyeon.imagicthud.platform.IPlatformHelpers;

public class ImagictHudClient {

    public static final String MODID = "imagicthud";
    public static final boolean DEBUG = false;
    private static ImagictHudConfig config;

    public static void init() {
        if (IPlatformHelpers.getInstance().isModLoaded(MODID)) {
            config = ImagictHudConfig.getInstance();
            config.load();
            LimitedConfigValue.setValueWithLimit(config);
            config.save();

            KeyBinds.register();
        }
    }

    public static ImagictHudConfig getConfig() {
        return config;
    }
}
