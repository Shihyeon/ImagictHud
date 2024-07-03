package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.LimitedConfigValue;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ImagictHudClient implements ClientModInitializer {

    public static KeyBinding imagictHudKeyBinding;
    public static ImagictHudConfig CONFIG;

    private long lastKeyPressTime = 0;
    private static final long KEY_PRESS_DELAY = 300;

    @Override
    public void onInitializeClient() {

        ImagictHudConfig.HANDLER.load();
        CONFIG = ImagictHudConfig.HANDLER.instance();

        imagictHudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.imagicthud.enableHud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_GRAVE_ACCENT,
                "key.categories.imagicthud"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            long currentTime = System.currentTimeMillis();

            if (imagictHudKeyBinding.isPressed() && (currentTime - lastKeyPressTime) >= KEY_PRESS_DELAY) {
                CONFIG.enableHud = !CONFIG.enableHud;
                lastKeyPressTime = currentTime;
            }
        });

        LimitedConfigValue.setValueWithLimit(CONFIG);
    }
}
