package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.modules.LegacySneakAnimation;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    private static final float SNEAKING_HEIGHT = 1.52f;

    @Shadow
    protected ItemStack activeItemStack;

    private boolean isLegacySneakingEnabled() {
        return OldAnimations.isEnabled(LegacySneakAnimation.class);
    }

    // Port of 1.7 sneaking eye height to newer versions.
    @Inject(at = @At("HEAD"), method = "getEyeHeight", cancellable = true)
    public final void processEyeHeight(EntityPose pose, EntityDimensions dimensions, CallbackInfoReturnable<Float> callbackInfo) {
        if(pose == EntityPose.CROUCHING && isLegacySneakingEnabled()) {
            callbackInfo.setReturnValue(SNEAKING_HEIGHT);
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "isBlocking", cancellable = true)
    public void processSwordBlock(CallbackInfoReturnable<Boolean> callbackInfo) {
        if(activeItemStack.getItem() instanceof SwordItem) {
            callbackInfo.setReturnValue(false);
        }
    }

}
