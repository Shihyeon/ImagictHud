package kr.shihyeon.imagicthud.mixin.gui;

import kr.shihyeon.imagicthud.client.ImagictHudClient;
import kr.shihyeon.imagicthud.config.ImagictHudConfig;
import kr.shihyeon.imagicthud.gui.Hud;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Shadow @Final
    private Minecraft minecraft;

    @Unique
    private static ImagictHudConfig config;

    @Inject(at = @At("HEAD"), method = "render")
    private void imagicthud$render(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        init();
        Hud.renderHud(context, minecraft, config);
    }

    @Unique
    private void init() {
        if (config == null) {
             config = ImagictHudClient.getConfig();
        }
    }
}
