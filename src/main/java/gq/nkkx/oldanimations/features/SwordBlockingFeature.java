package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.client.OldAnimationsClient;
import gq.nkkx.oldanimations.features.context.ItemRenderingFeatureExecutionContext;
import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import gq.nkkx.oldanimations.utils.Lazy;
import gq.nkkx.oldanimations.utils.PlayerEntityModelAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Arm;
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

    private boolean hasSwordAndShield(LivingEntity entity) {
        return entity.getEquippedStack(EquipmentSlot.OFFHAND).getItem() instanceof ShieldItem && entity.getEquippedStack(EquipmentSlot.MAINHAND).getItem() instanceof SwordItem;
    }

    private boolean isSwordBlocking(LivingEntity entity) {
        return hasSwordAndShield(entity) && entity.getActiveItem().getItem() instanceof ShieldItem && entity.isUsingItem();
    }

    public boolean shouldHideItem(LivingEntity entity, Hand hand) {
        return hasSwordAndShield(entity) && hand == Hand.OFF_HAND;
    }

    public boolean shouldHideItem(LivingEntity entity, ItemStack stack) {
        return hasSwordAndShield(entity) && stack.getItem() instanceof ShieldItem;
    }

    @Override
    public void transform(ItemRenderingFeatureExecutionContext context) {
        if(isSwordBlocking(context.player())) {
            int side = context.player().getMainArm() == Arm.RIGHT ? 1 : -1;
            MatrixStack matrices = context.matrices().stack();
            matrices.translate(side * -0.14142136f, 0.08f, 0.14142136f);
            matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-102.25f));
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(side * 13.365f));
            matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(side * 78.05f));
        }
    }

    @Override
    public void transformThirdPersonItem(LivingEntity livingEntity, ItemRenderingMatrices matrices) {
        if(isSwordBlocking(livingEntity)) {
            MatrixStack stack = matrices.stack();
            if (livingEntity.getMainArm() == Arm.RIGHT) {
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
            ModelPart mainArm = (livingEntity.getMainArm() == Arm.RIGHT ? playerEntityModel.rightArm :
                    playerEntityModel.leftArm);
            mainArm.pitch = -0.75f;
        }
    }
}
