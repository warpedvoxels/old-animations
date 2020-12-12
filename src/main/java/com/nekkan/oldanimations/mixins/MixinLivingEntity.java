package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.modules.LegacySneakAnimation;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {

    private static final float LEGACY_DEFAULT_EYE_HEIGHT = 1.608f;
    private static final float DEFAULT_EYE_HEIGHT = 1.62f;
    private static final float LEGACY_SNEAKING_HEIGHT = 1.52f;
    private static final float SNEAKING_HEIGHT = 1.27f;
    private static final float SLEEPING_HEIGHT = 0.2f;
    private static final float SPIN_ATTACK_HEIGHT = 0.4f;
    private Boolean hasLegacyAnimation = null;

    private boolean getLegacyAnimation() {
        if(hasLegacyAnimation == null) {
            hasLegacyAnimation = OldAnimations.isEnabled(LegacySneakAnimation.class);
        }
        return hasLegacyAnimation;
    }

    // Backport of 1.7 eye height to newer versions.

    /**
     * @author ?
     */
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
                return getLegacyAnimation() ? LEGACY_SNEAKING_HEIGHT : SNEAKING_HEIGHT;
            default:
                return getLegacyAnimation() ? LEGACY_DEFAULT_EYE_HEIGHT : DEFAULT_EYE_HEIGHT;
        }
    }

}
