package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimationsConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class MixinItem {

    private boolean isAnimationEnabled() {
        OldAnimationsConfig config = AutoConfig.getConfigHolder(OldAnimationsConfig.class).getConfig();
        return config.isEnableSwordBlockingAnimation() && !config.isEnableSwordBlockingAnimationShield();
    }

    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    private void processUse(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> callbackInfo) {
        if(!isAnimationEnabled()) {
            return;
        }
        ItemStack item = player.getStackInHand(hand);
        if(item.getItem() instanceof SwordItem) {
            ItemStack offHandItem = player.getStackInHand(Hand.OFF_HAND);
            if(offHandItem.getItem() == Items.SHIELD) {
                player.setCurrentHand(Hand.OFF_HAND);
                callbackInfo.cancel();
            }
            player.setCurrentHand(hand);
            player.setSprinting(false);
            callbackInfo.setReturnValue(TypedActionResult.consume(item));
        }
    }

    @Inject(at = @At("HEAD"), method = "getUseAction", cancellable = true)
    public void processUseAction(ItemStack item, CallbackInfoReturnable<UseAction> callbackInfo) {
        if(item.getItem() instanceof SwordItem && isAnimationEnabled()) {
            callbackInfo.setReturnValue(UseAction.BLOCK);
        }
    }

}
