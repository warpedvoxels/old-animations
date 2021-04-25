package gq.nkkx.oldanimations.client.mixin.render;

import gq.nkkx.oldanimations.renderer.OldAnimationsCamera;
import gq.nkkx.oldanimations.utils.CameraAccess;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin implements CameraAccess {

    private static final OldAnimationsCamera camera = new OldAnimationsCamera();

    @Accessor("cameraY")
    public abstract void setCameraY(float cameraY);

    @Accessor("focusedEntity")
    public abstract Entity getFocusedEntity();

    @Inject(method = "updateEyeHeight", at = @At(value = "FIELD", target = "Lnet/minecraft/client/render/Camera;cameraY:F", ordinal = 0))
    public void old_animations$updateEyeHeight(CallbackInfo callbackInfo) {
        camera.removeSmoothing(this, callbackInfo);
    }

}
