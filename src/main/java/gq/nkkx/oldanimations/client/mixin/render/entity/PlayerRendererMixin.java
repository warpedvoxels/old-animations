package gq.nkkx.oldanimations.client.mixin.render.entity;

import gq.nkkx.oldanimations.features.SwordBlockingFeature;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRendererMixin {

    @Inject(at = @At("HEAD"), method = "getArmPose", cancellable = true)
    private static void old_animations$getArmPose(AbstractClientPlayerEntity abstractClientPlayerEntity, Hand hand,
            CallbackInfoReturnable<BipedEntityModel.ArmPose> cir) {
        if (SwordBlockingFeature.isEnabled() && SwordBlockingFeature.LAZY.get().shouldHideItem(abstractClientPlayerEntity, hand)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.EMPTY);
        }
    }

}
