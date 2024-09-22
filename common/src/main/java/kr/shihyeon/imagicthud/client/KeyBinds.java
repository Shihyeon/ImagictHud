package kr.shihyeon.imagicthud.client;

import com.mojang.blaze3d.platform.InputConstants;
import kr.shihyeon.imagicthud.platform.IPlatformHelpers;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyBinds {

    private static KeyMapping hudKeyBinding;

    public static void register() {
        hudKeyBinding = IPlatformHelpers.getInstance().registerKeyBinding(new KeyMapping(
                "key.imagicthud.toggle_hud",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_GRAVE_ACCENT,
                "key.categories.imagicthud"
        ));
    }

    public static KeyMapping getHudKeyBinding() {
        return hudKeyBinding;
    }
}
