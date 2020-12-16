package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.modules.LegacySneakAnimation;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    private static final float DEFAULT_EYE_HEIGHT = 1.62f;
    private static final float LEGACY_SNEAKING_HEIGHT = 1.52f;
    private static final float SNEAKING_HEIGHT = 1.27f;
    private static final float SLEEPING_HEIGHT = 0.2f;
    private static final float SPIN_ATTACK_HEIGHT = 0.4f;

    @Shadow
    protected ItemStack activeItemStack;

    private boolean isLegacySneakingEnabled() {
        return OldAnimations.isEnabled(LegacySneakAnimation.class);
    }

    // Backport of 1.7 eye height to newer versions.
    @Overwrite
    public final float getEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        switch(pose) {
            case SWIMMING:
            case FALL_FLYING:
            case SPIN_ATTACK:
                return SPIN_ATTACK_HEIGHT;
            case SLEEPING:
                return SLEEPING_HEIGHT;
            case CROUCHING:
                return isLegacySneakingEnabled() ? LEGACY_SNEAKING_HEIGHT : SNEAKING_HEIGHT;
            default:
                return DEFAULT_EYE_HEIGHT;
        }
    }

    @Inject(at = @At(value = "HEAD"), method = "isBlocking", cancellable = true)
    public void processSwordBlock(CallbackInfoReturnable<Boolean> cir) {
        Item item = this.activeItemStack.getItem();
        if(item instanceof SwordItem) {
            cir.setReturnValue(false);
        }
    }

}
