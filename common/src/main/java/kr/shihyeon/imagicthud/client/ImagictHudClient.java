package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.LimitedConfigValue;
import kr.shihyeon.imagicthud.platform.IPlatformHelpers;

public class ImagictHudClient {

    public static final String MODID = "imagicthud";
    public static final boolean DEBUG = false;
    public static ImagictHudConfig CONFIG;

    public static void init() {
        if (IPlatformHelpers.getInstance().isModLoaded(MODID)) {
            CONFIG = ImagictHudConfig.getInstance();
            CONFIG.load();
            LimitedConfigValue.setValueWithLimit(CONFIG);
            CONFIG.save();

            KeyBinds.register();
        }
    }
}
