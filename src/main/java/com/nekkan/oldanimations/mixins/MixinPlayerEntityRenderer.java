package com.nekkan.oldanimations.mixins;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class MixinPlayerEntityRenderer {

    // Return a blocking callback if the player is using shield to Fabric apply the models.
    @Inject(at = @At("HEAD"), method = "getArmPose", cancellable = true)
    private static void applyArmPose(
        AbstractClientPlayerEntity abstractClientPlayerEntity,
        Hand hand,
        CallbackInfoReturnable<ArmPose> callbackInfo
    ) {
        Item item = abstractClientPlayerEntity.getOffHandStack().getItem();
        ArmPose pose = item == Items.SHIELD && abstractClientPlayerEntity.isUsingItem() ? ArmPose.BLOCK : ArmPose.EMPTY;
        callbackInfo.setReturnValue(pose);
    }

}
