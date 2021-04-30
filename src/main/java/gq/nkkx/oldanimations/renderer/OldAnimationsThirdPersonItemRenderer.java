package gq.nkkx.oldanimations.renderer;

import gq.nkkx.oldanimations.features.SwordBlockingFeature;
import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import net.minecraft.entity.LivingEntity;

public class OldAnimationsThirdPersonItemRenderer {

    public void transformThirdPersonItem(LivingEntity entity, ItemRenderingMatrices matrices) {
        if (SwordBlockingFeature.isEnabled()) {
            SwordBlockingFeature.LAZY.get().transformThirdPersonItem(entity, matrices);
        }
    }

}
