package gq.nkkx.oldanimations.client.mixin.item;

import gq.nkkx.oldanimations.behavior.OldAnimationsSwordBehavior;
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

    private static final OldAnimationsSwordBehavior behavior = new OldAnimationsSwordBehavior();

    @Inject(at = @At("HEAD"), method = "getUseAction", cancellable = true)
    public void old_animations$getUseAction(ItemStack item, CallbackInfoReturnable<UseAction> callbackInfo) {
        behavior.injectUseAction(callbackInfo);
    }

    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void old_animations$use(
        World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> callbackInfo
    ) {
        behavior.use(player, hand, callbackInfo);
    }

}
