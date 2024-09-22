package kr.shihyeon.imagicthud.mixin;

import kr.shihyeon.imagicthud.client.ImagictHudClientManager;
import kr.shihyeon.imagicthud.client.KeyBinds;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public abstract class KeyboardHandlerMixin {

    @Shadow @Final
    private Minecraft minecraft;

    @Inject(at = @At("HEAD"), method = "keyPress", cancellable = true)
    private void onKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action != 1 && minecraft.screen == null
                && KeyBinds.getHudKeyBinding().matches(keyCode, scanCode)) {
            ImagictHudClientManager.toggleHud();
            ci.cancel();
        }
    }
}
