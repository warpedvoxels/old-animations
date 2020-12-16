package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.modules.LegacyBowPosition;
import com.nekkan.oldanimations.modules.LegacyRodPosition;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class MixinHeldItemRenderer {

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
        ),
        method = "renderFirstPersonItem",
        cancellable = true
    )
    private void renderFirstPersonItem(
        AbstractClientPlayerEntity player,
        float tickDelta,
        float pitch,
        Hand hand,
        float swingProgress,
        ItemStack item,
        float equipProgress,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light,
        CallbackInfo callbackInfo
    ) {
        if(Items.BOW.equals(item.getItem()) && OldAnimations.isEnabled(LegacyBowPosition.class)) {
            Vector3f scale = LegacyBowPosition.INSTANCE.getScale();
            Vector3f position = LegacyBowPosition.INSTANCE.getPosition();
            matrices.translate(position.getX(), position.getY(), position.getZ());
            matrices.scale(scale.getX(), scale.getY(), scale.getZ());
            return;
        }
        if(Items.FISHING_ROD.equals(item.getItem()) && OldAnimations.isEnabled(LegacyRodPosition.class)) {
            Vector3f scale = LegacyRodPosition.INSTANCE.getScale();
            Vector3f position = LegacyRodPosition.INSTANCE.getPosition();
            matrices.translate(position.getX(), position.getY(), position.getZ());
            matrices.scale(scale.getX(), scale.getY(), scale.getZ());
        }
    }

}
