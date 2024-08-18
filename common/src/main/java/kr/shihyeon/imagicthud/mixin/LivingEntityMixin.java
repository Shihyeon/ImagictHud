package kr.shihyeon.imagicthud.mixin;

import kr.shihyeon.imagicthud.util.EntityTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("TAIL"), method = "handleDamageEvent(Lnet/minecraft/world/damagesource/DamageSource;)V")
    private void onEntityDamage(DamageSource damageSource, CallbackInfo callbackInfo) {
        EntityTracker.onDamage(damageSource, ((LivingEntity) (Object) this));
    }
}