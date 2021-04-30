package gq.nkkx.oldanimations.client.mixin.entity;

import gq.nkkx.oldanimations.behavior.OldAnimationsLivingEntityBehavior;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    private static final OldAnimationsLivingEntityBehavior behavior = new OldAnimationsLivingEntityBehavior();

    @Inject(at = @At(value = "HEAD"), method = "isBlocking", cancellable = true)
    private void old_animations$isBlocking(CallbackInfoReturnable<Boolean> callbackInfo) {
        LivingEntity entity = (LivingEntity) (Object) this;
        behavior.modifyIsBlockingBehavior(entity, entity.getActiveItem(), callbackInfo);
    }

}
