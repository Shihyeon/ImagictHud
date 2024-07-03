package kr.shihyeon.imagicthud.mixin;

import kr.shihyeon.imagicthud.hud.Hud;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Shadow @Final
    private MinecraftClient client;

    @Inject(at = @At("HEAD"), method = "render", cancellable = true)
    public void render(DrawContext context, float tickDelta, CallbackInfo ci) {
        Hud.renderHud(context, client);
    }
}
