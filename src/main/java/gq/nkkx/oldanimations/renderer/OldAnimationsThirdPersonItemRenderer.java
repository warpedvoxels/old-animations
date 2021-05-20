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
            if (SwordBlockingFeature.hasSwordAndShield(entity)) {
                if (SwordBlockingFeature.isOffhand(entity, item)) {
                    callback.cancel();
                    return;
                }
            } else {
                return;
            }
            if (SwordBlockingFeature.isSwordBlocking(entity)) {
                SwordBlockingFeature.LAZY.get().transformThirdPersonItem(entity, matrices);
            }
        }
    }

}
