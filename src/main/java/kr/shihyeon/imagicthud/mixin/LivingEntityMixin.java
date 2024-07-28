package kr.shihyeon.imagicthud.mixin;

import kr.shihyeon.imagicthud.util.EntityTracker;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Inject(at = @At("TAIL"), method = "onDamaged(Lnet/minecraft/entity/damage/DamageSource;)V")
    private void onEntityDamage(DamageSource damageSource, CallbackInfo callbackInfo) {
        EntityTracker.onDamage(damageSource, ((LivingEntity) (Object) this));
    }
}