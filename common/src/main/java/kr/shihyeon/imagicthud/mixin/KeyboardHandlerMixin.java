package kr.shihyeon.imagicthud.mixin;

import kr.shihyeon.imagicthud.client.ImagictHudClientManager;
import kr.shihyeon.imagicthud.client.KeyBinds;
import kr.shihyeon.imagicthud.gui.screen.config.yacl3.YaclConfigScreenFactoryManager;
import kr.shihyeon.imagicthud.platform.IPlatformHelpers;
import kr.shihyeon.imagicthud.util.ModLogger;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {

    @Shadow @Final
    private Minecraft minecraft;

    @Inject(at = @At("HEAD"), method = "keyPress", cancellable = true)
    private void onKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action != 1 && minecraft.screen == null) {
            toggleHud(keyCode, scanCode, ci);
            showConfigScreen(keyCode, scanCode, ci);
        }
    }

    @Unique
    private void toggleHud(int keyCode, int scanCode, CallbackInfo ci) {
        if (KeyBinds.getHudKeyBinding().matches(keyCode, scanCode)) {
            ImagictHudClientManager.toggleHud();
            ci.cancel();
        }
    }

    @Unique
    private void showConfigScreen(int keyCode, int scanCode, CallbackInfo ci) {
        if (KeyBinds.getConfigKeyBinding().matches(keyCode, scanCode)) {
            if (IPlatformHelpers.getInstance().isModLoaded("yet_another_config_lib_v3")) {
                minecraft.setScreen(YaclConfigScreenFactoryManager.createScreen(minecraft.screen));
            } else {
                ModLogger.error("yacl3 is not installed.");
            }
            ci.cancel();
        }
    }
}
