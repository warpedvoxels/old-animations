package gq.nkkx.oldanimations.renderer;

import gq.nkkx.oldanimations.features.SwordBlockingFeature;
import gq.nkkx.oldanimations.utils.PlayerEntityModelAccess;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class OldAnimationsThirdPersonModelRenderer {

    public void transformThirdPersonModel(LivingEntity entity,
                PlayerEntityModelAccess entityModelAccess,
                float ticks) {
        if (SwordBlockingFeature.isEnabled() && SwordBlockingFeature.isSwordBlocking(entity)) {
            SwordBlockingFeature.LAZY.get().transformThirdPersonEntity(entityModelAccess, entity, ticks);
        }
    }

}
