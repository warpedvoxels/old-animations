package gq.nkkx.oldanimations.renderer;

import gq.nkkx.oldanimations.features.SwordBlockingFeature;
import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class OldAnimationsThirdPersonItemRenderer {

    public void transformThirdPersonItem(LivingEntity entity, ItemStack item,
            ItemRenderingMatrices matrices, CallbackInfo callback) {
        if (SwordBlockingFeature.isEnabled()) {
            SwordBlockingFeature feature = SwordBlockingFeature.LAZY.get();
            if (feature.shouldHideItem(entity, item)) {
                callback.cancel();
                return;
            }
            feature.transformThirdPersonItem(entity, matrices);
        }
    }

}
