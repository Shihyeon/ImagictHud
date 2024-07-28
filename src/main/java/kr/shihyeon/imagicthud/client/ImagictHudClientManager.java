package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;

public class ImagictHudClientManager {

    public static ImagictHudConfig config = ImagictHudClient.CONFIG;

    public static void toggleHud() {
        config.hud.general.enableHud = !config.hud.general.enableHud;
    }
}
