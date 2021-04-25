package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.OldAnimations;
import gq.nkkx.oldanimations.features.context.ItemRenderingFeatureExecutionContext;
import gq.nkkx.oldanimations.utils.Lazy;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class SwordBlockingFeature implements ItemRenderingFeature<ItemRenderingFeatureExecutionContext> {

    public static final Lazy<SwordBlockingFeature> LAZY = Lazy.create(SwordBlockingFeature::new);

    public static boolean isEnabled() {
        return OldAnimations.options().getSwordBlocking().isEnabled();
    }

    public void use(PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> callbackInfo) {
        ItemStack item = player.getStackInHand(hand);
        if (item.getItem() instanceof SwordItem) {
            player.setSprinting(false);
            player.setCurrentHand(hand);
            callbackInfo.setReturnValue(TypedActionResult.pass(item));
        }
    }

    @Override
    public void transform(ItemRenderingFeatureExecutionContext context) {
        if (context.player().getStackInHand(Hand.MAIN_HAND).getItem() instanceof ShieldItem ||
            context.player().getStackInHand(Hand.OFF_HAND).getItem() instanceof ShieldItem) return;

        int side = context.hand() == Hand.MAIN_HAND ? 1 : -1;
        MatrixStack matrices = context.matrices().stack();
        matrices.translate(side * -0.14142136F, 0.08F, 0.14142136F);
        matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-102.25f));
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(side * 13.365f));
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(side * 78.05f));
    }

}
