package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.LimitedConfigValue;
import kr.shihyeon.imagicthud.util.EntityTracker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientEntityEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ImagictHudClient implements ClientModInitializer {

    private static KeyBinding hudKeyBinding;
    public static final boolean DEBUG = false;
    public static ImagictHudConfig CONFIG;

    @Override
    public void onInitializeClient() {
        CONFIG = ImagictHudConfig.INSTANCE;
        CONFIG.load();

        //KeyBinds.register();
        hudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.imagicthud.toggle_hud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_GRAVE_ACCENT,
                "key.categories.imagicthud"
        ));

        registerEvents();

        init();
    }

    private static void init() {
        LimitedConfigValue.setValueWithLimit(CONFIG);
        CONFIG.save();
    }

    private void registerEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(EntityTracker::tick);

        ClientEntityEvents.ENTITY_UNLOAD.register(
                (entity, world) -> EntityTracker.removeFromUUIDS(entity)
        );
    }

    public static KeyBinding getHudKeyBinding() {
        return hudKeyBinding;
    }
}
