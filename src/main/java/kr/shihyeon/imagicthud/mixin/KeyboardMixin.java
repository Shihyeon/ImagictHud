package kr.shihyeon.imagicthud.mixin;

import kr.shihyeon.imagicthud.client.ImagictHudClientManager;
import kr.shihyeon.imagicthud.client.KeyBinds;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Shadow @Final
    private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "onKey", cancellable = true)
    public void onKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo ci) {
        if (action != 1 && client.currentScreen == null
                && KeyBinds.getHudKeyBinding().matchesKey(keyCode, scanCode)) {
            ImagictHudClientManager.toggleHud();
            ci.cancel();
        }
    }
}
