package gq.nkkx.oldanimations.behavior;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class OldAnimationsLivingEntityBehavior implements IBehavior {

    public void modifyIsBlockingBehavior(LivingEntity livingEntity, ItemStack activeItemStack, CallbackInfoReturnable<Boolean> callbackInfo) {
        if (activeItemStack.getItem() instanceof SwordItem) {
            callbackInfo.setReturnValue(false);
        }
    }

}
