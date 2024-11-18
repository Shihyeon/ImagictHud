package kr.shihyeon.imagicthud.mixin;

import kr.shihyeon.imagicthud.client.ImagictHudClientManager;
import kr.shihyeon.imagicthud.client.KeyBinds;
import kr.shihyeon.imagicthud.gui.screen.config.yacl3.YaclConfigScreenFactoryManager;
import kr.shihyeon.imagicthud.platform.IPlatformHelpers;
import kr.shihyeon.imagicthud.util.ModLogger;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
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
    private void imagicthud$onKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action != 1 && minecraft.screen == null) {
            imagicthud$toggleHud(keyCode, scanCode, ci);
            imagicthud$openConfigScreen(keyCode, scanCode, ci);
        }
    }

    @Unique
    private void imagicthud$toggleHud(int keyCode, int scanCode, CallbackInfo ci) {
        if (KeyBinds.getHudKeyBinding().matches(keyCode, scanCode)) {
            ImagictHudClientManager.toggleHud();
            ci.cancel();
        }
    }

    @Unique
    private void imagicthud$openConfigScreen(int keyCode, int scanCode, CallbackInfo ci) {
        if (KeyBinds.getConfigKeyBinding().matches(keyCode, scanCode)) {
            if (IPlatformHelpers.getInstance().isModLoaded("yet_another_config_lib_v3")) {
                minecraft.setScreen(YaclConfigScreenFactoryManager.createScreen(minecraft.screen));
            } else {
                ModLogger.error("YACL3 is not installed. You need to YetAnotherConfigLib!");
                if (minecraft.player != null) {
                    minecraft.player.displayClientMessage(Component.literal("You need to YetAnotherConfigLib!"), false);
                }
            }
            ci.cancel();
        }
    }
}
