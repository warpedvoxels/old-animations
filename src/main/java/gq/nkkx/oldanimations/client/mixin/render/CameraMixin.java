package gq.nkkx.oldanimations.client.mixin.render;

import gq.nkkx.oldanimations.features.OldSneakingFeature;
import gq.nkkx.oldanimations.features.context.FeatureExecutionContext;
import gq.nkkx.oldanimations.utils.CameraAccess;
import gq.nkkx.oldanimations.utils.Lazy;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin implements CameraAccess {

    private final Lazy<OldSneakingFeature> feature = Lazy.create(OldSneakingFeature::new);

    @Accessor("cameraY")
    public abstract void setCameraY(float cameraY);

    @Accessor("focusedEntity")
    public abstract Entity getFocusedEntity();

    @Inject(method = "updateEyeHeight", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/Camera;cameraY:F", ordinal = 0))
    public void old_animations$updateEyeHeight(CallbackInfo callbackInfo) {
        OldSneakingFeature feature = this.feature.get();
        if (feature.isEnabled()) {
            FeatureExecutionContext context = FeatureExecutionContext.create(callbackInfo);
            feature.transform(context);
        }
    }

}
