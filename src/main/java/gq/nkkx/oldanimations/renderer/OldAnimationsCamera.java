package gq.nkkx.oldanimations.renderer;

import gq.nkkx.oldanimations.features.OldSneakingFeature;
import gq.nkkx.oldanimations.features.context.FeatureExecutionContext;
import gq.nkkx.oldanimations.utils.CameraAccess;
import gq.nkkx.oldanimations.utils.Lazy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class OldAnimationsCamera {

    private static final Lazy<OldSneakingFeature> feature = Lazy.create(OldSneakingFeature::new);

    public void removeSmoothing(CameraAccess camera, CallbackInfo callbackInfo) {
        if (OldSneakingFeature.isEnabled()) {
            Entity focusedEntity = camera.getFocusedEntity();
            if (!(focusedEntity instanceof PlayerEntity)) {
                return;
            }
            FeatureExecutionContext context = FeatureExecutionContext.create((PlayerEntity) focusedEntity, callbackInfo);
            feature.get().transform(context);
        }
    }

}
