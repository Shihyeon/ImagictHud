package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.LimitedConfigValue;
import kr.shihyeon.imagicthud.util.EntityTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class ImagictHudClient implements ClientModInitializer {

    public static ImagictHudConfig CONFIG;

    @Override
    public void onInitializeClient() {
        ImagictHudConfig.HANDLER.load();
        CONFIG = ImagictHudConfig.HANDLER.instance();

        KeyBinds.register();

        registerEvents();

        init();
    }

    private static void init() {
        LimitedConfigValue.setValueWithLimit(CONFIG);
        ImagictHudConfig.HANDLER.save();
    }

    public void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(EntityTracker::tick);

        ClientEntityEvents.ENTITY_UNLOAD.register((entity, world) -> {
            EntityTracker.removeFromUUIDS(entity);
        });
    }

    public static void toggleHud() {
        CONFIG.enableHud = !CONFIG.enableHud;
    }
}
