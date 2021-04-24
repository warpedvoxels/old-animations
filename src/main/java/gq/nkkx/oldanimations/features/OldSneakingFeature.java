package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.OldAnimations;
import gq.nkkx.oldanimations.features.context.FeatureExecutionContext;
import gq.nkkx.oldanimations.options.OldSneakingFeatureOptions;
import gq.nkkx.oldanimations.utils.CameraAccess;
import gq.nkkx.oldanimations.utils.Lazy;
import net.minecraft.client.MinecraftClient;

public class OldSneakingFeature implements OldAnimationsFeature<FeatureExecutionContext> {

    private static final Lazy<OldSneakingFeatureOptions> options = Lazy.create(() -> OldAnimations.options().getOldSneaking());
    private static final float LEGACY_SNEAKING_EYE_HEIGHT = 1.27f;

    @Override
    public boolean isEnabled() {
        return options.get().isEnabled();
    }

    @Override
    public void transform(FeatureExecutionContext context) {
        OldSneakingFeatureOptions.Mode mode = options.get().mode();
        CameraAccess camera = (CameraAccess) MinecraftClient.getInstance().gameRenderer.getCamera();
        camera.setCameraY(
            mode == OldSneakingFeatureOptions.Mode.JUST_REMOVE_SMOOTHING
                ? camera.getFocusedEntity().getStandingEyeHeight()
                : LEGACY_SNEAKING_EYE_HEIGHT
        );
    }

}
