package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.client.OldAnimationsClient;
import gq.nkkx.oldanimations.features.context.FeatureExecutionContext;
import gq.nkkx.oldanimations.utils.CameraAccess;
import net.minecraft.client.MinecraftClient;

public class OldSneakingFeature implements OldAnimationsFeature<FeatureExecutionContext> {

    // Unnecessary assignment, since OldAnimations.options() is a Lazy too.
    // private static final Lazy<OldSneakingFeatureOptions> options = Lazy.create(() -> OldAnimations.options().getOldSneaking());

    public static boolean isEnabled() {
        return OldAnimationsClient.options().getOldSneaking().isEnabled();
    }

    @Override
    public void transform(FeatureExecutionContext context) {
        CameraAccess camera = (CameraAccess) MinecraftClient.getInstance().gameRenderer.getCamera();
        camera.setCameraY(camera.getFocusedEntity().getStandingEyeHeight());
    }

}
