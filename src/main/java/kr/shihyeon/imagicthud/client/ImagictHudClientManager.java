package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;

public class ImagictHudClientManager {

    public static ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static void toggleHud() {
        config.general.hud.enableHud = !config.general.hud.enableHud;
    }
}
