package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.client.OldAnimationsClient;
import gq.nkkx.oldanimations.features.context.ItemRenderingFeatureExecutionContext;
import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import gq.nkkx.oldanimations.utils.Lazy;
import gq.nkkx.oldanimations.utils.PlayerEntityModelAccess;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Quaternion;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class SwordBlockingFeature implements ItemRenderingFeature<ItemRenderingFeatureExecutionContext>, ThirdPersonTransformer {

    public static final Lazy<SwordBlockingFeature> LAZY = Lazy.create(SwordBlockingFeature::new);
    private static final Quaternion THIRD_PERSON_QUATERNION = new Quaternion(Vector3f.POSITIVE_Y, -45f, true);
    private static final Quaternion NEGATIVE_THIRD_PERSON_QUATERNION = new Quaternion(Vector3f.POSITIVE_Y, 45f, true);

    public static boolean isEnabled() {
        return OldAnimationsClient.options().getSwordBlocking().isEnabled();
    }

    private boolean isSwordBlocking(LivingEntity livingEntity) {
        if (livingEntity.getStackInHand(Hand.MAIN_HAND).getItem() instanceof ShieldItem || livingEntity.getStackInHand(Hand.OFF_HAND).getItem() instanceof ShieldItem) {
            return false;
        }
        return livingEntity.getActiveItem().getItem() instanceof SwordItem && livingEntity.isUsingItem();
    }

    public void use(PlayerEntity player, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> callbackInfo) {
        ItemStack item = player.getStackInHand(hand);
        if (item.getItem() instanceof SwordItem && !(player.getStackInHand(hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND).getItem() instanceof ShieldItem)) {
            player.setSprinting(false);
            player.setCurrentHand(hand);
            callbackInfo.setReturnValue(TypedActionResult.pass(item));
        }
    }

    @Override
    public void transform(ItemRenderingFeatureExecutionContext context) {
        if (context.player().getStackInHand(context.hand() == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND).getItem() instanceof ShieldItem) {
            return;
        }
        int side = context.hand() == Hand.MAIN_HAND ? 1 : -1;
        MatrixStack matrices = context.matrices().stack();
        matrices.translate(side * -0.14142136f, 0.08f, 0.14142136f);
        matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-102.25f));
        matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(side * 13.365f));
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(side * 78.05f));
    }

    @Override
    public void transformThirdPersonItem(LivingEntity livingEntity, ItemRenderingMatrices matrices) {
        if (isSwordBlocking(livingEntity)) {
            MatrixStack stack = matrices.stack();
            if (livingEntity.getStackInHand(Hand.MAIN_HAND).getItem() instanceof SwordItem) {
                stack.translate(-0.14142136f, -0.05f, 0.14142136f);
                stack.multiply(THIRD_PERSON_QUATERNION);
            } else {
                stack.translate(0.14142136f, -0.05f, 0.14142136f);
                stack.multiply(NEGATIVE_THIRD_PERSON_QUATERNION);
            }
        }
    }

    @Override
    public void transformThirdPersonEntity(PlayerEntityModelAccess model, LivingEntity livingEntity, float ticks) {
        if (isSwordBlocking(livingEntity)) {
            PlayerEntityModel<?> playerEntityModel = (PlayerEntityModel<?>) model;
            int side = livingEntity.getStackInHand(Hand.MAIN_HAND).getItem() instanceof SwordItem ? 1 : -1;
            (side == 1 ? playerEntityModel.rightArm : playerEntityModel.leftArm).pitch = -0.75f;
        }
    }
}
