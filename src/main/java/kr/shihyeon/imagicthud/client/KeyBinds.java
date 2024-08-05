package kr.shihyeon.imagicthud.client;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {

    private static KeyBinding hudKeyBinding;

    public static void register() {
        hudKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.imagicthud.toggle_hud",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_GRAVE_ACCENT,
                "key.categories.imagicthud"
        ));
    }

    public static KeyBinding getHudKeyBinding() {
        return hudKeyBinding;
    }
}
