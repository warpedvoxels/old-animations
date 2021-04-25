package gq.nkkx.oldanimations.client.mixin.item;

import gq.nkkx.oldanimations.OldAnimations;
import gq.nkkx.oldanimations.features.SwordBlockingFeature;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At("HEAD"), method = "getUseAction", cancellable = true)
    public void old_animations$getUseAction(ItemStack item, CallbackInfoReturnable<UseAction> callbackInfo) {
        if (OldAnimations.options().getSwordBlocking().isEnabled()) {
            callbackInfo.setReturnValue(UseAction.BLOCK);
        }
    }

    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void old_animations$use(
        World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> callbackInfo
    ) {
        if (SwordBlockingFeature.isEnabled()) {
            SwordBlockingFeature.LAZY.get().use(player, hand, callbackInfo);
        }
    }

}
