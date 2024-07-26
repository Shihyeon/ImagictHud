package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.LimitedConfigValue;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ImagictHudClient implements ClientModInitializer {

    public static ImagictHudConfig CONFIG;

    @Override
    public void onInitializeClient() {

        ImagictHudConfig.HANDLER.load();
        CONFIG = ImagictHudConfig.HANDLER.instance();

        KeyBinds.register();
        init();
    }

    private static void init() {
        LimitedConfigValue.setValueWithLimit(CONFIG);
        ImagictHudConfig.HANDLER.save();
    }

    public static void toggleHud() {
        CONFIG.enableHud = !CONFIG.enableHud;
    }
}
