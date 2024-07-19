package kr.shihyeon.imagicthud.client;

import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.config.LimitedConfigValue;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ImagictHudClient implements ClientModInitializer {

    public static KeyBinding hudKeyBinding;
    public static ImagictHudConfig CONFIG;

    @Override
    public void onInitializeClient() {

        ImagictHudConfig.HANDLER.load();
        CONFIG = ImagictHudConfig.HANDLER.instance();

        hudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.imagicthud.toggle_hud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_GRAVE_ACCENT,
                "key.categories.imagicthud"
        ));

        LimitedConfigValue.setValueWithLimit(CONFIG);
        ImagictHudConfig.HANDLER.save();
    }

    public static void toggleHud() {
        CONFIG.enableHud = !CONFIG.enableHud;
    }
}
