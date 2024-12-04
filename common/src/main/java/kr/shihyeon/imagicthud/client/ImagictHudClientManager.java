package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;

public class ImagictHudClientManager {

    public static final ImagictHudConfig CONFIG = ImagictHudClient.getConfig();

    public static void toggleHud() {
        CONFIG.hud.general.enableHud = !CONFIG.hud.general.enableHud;
    }
}
