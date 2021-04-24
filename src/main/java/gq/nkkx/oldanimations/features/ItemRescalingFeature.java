package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.OldAnimations;
import gq.nkkx.oldanimations.features.context.ItemRenderingFeatureExecutionContext;
import gq.nkkx.oldanimations.registry.ItemRescalingRegistry;
import gq.nkkx.oldanimations.utils.ItemRescaling;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;

import java.util.Optional;

public class ItemRescalingFeature implements ItemRenderingFeature<ItemRenderingFeatureExecutionContext> {

    // Unnecessary assignment, since OldAnimations.options() is a Lazy too.
    // private static final Lazy<OldSneakingFeatureOptions> options = Lazy.create(() -> OldAnimations.options().getRescaling());

    public static boolean isEnabled() {
        return OldAnimations.options().getRescaling().isEnabled();
    }

    @Override
    public void transform(ItemRenderingFeatureExecutionContext context) {
        Optional<ItemRescaling> rescaling = ItemRescalingRegistry.find(context.itemStack().getItem());
        if (rescaling.isPresent()) {
            Vector3f position = rescaling.get().position();
            Vector3f scale = rescaling.get().scale();
            MatrixStack matrices = context.matrices().stack();
            matrices.translate(position.getX(), position.getY(), position.getZ());
            matrices.scale(scale.getX(), scale.getY(), scale.getZ());
        }
    }

}
