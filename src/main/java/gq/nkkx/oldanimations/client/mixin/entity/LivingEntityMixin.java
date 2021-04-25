package gq.nkkx.oldanimations.client.mixin.entity;

import gq.nkkx.oldanimations.behavior.OldAnimationsLivingEntityBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    private static final OldAnimationsLivingEntityBehavior behavior = new OldAnimationsLivingEntityBehavior();

    @Shadow
    protected ItemStack activeItemStack;

    @Inject(at = @At(value = "HEAD"), method = "isBlocking", cancellable = true)
    private void old_animations$isBlocking(CallbackInfoReturnable<Boolean> callbackInfo) {
        behavior.modifyIsBlockingBehavior(activeItemStack, callbackInfo);
    }

}
