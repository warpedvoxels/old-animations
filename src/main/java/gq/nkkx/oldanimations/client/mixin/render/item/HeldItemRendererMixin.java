package gq.nkkx.oldanimations.client.mixin.render.item;

import gq.nkkx.oldanimations.features.ItemRescalingFeature;
import gq.nkkx.oldanimations.features.SwordBlockingFeature;
import gq.nkkx.oldanimations.features.context.ItemRenderingFeatureExecutionContext;
import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import gq.nkkx.oldanimations.features.context.ItemRenderingProgress;
import gq.nkkx.oldanimations.utils.Lazy;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    private static final Lazy<ItemRescalingFeature> itemRescalingFeature = Lazy.create(ItemRescalingFeature::new);

    @Inject(
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
        ),
        method = "renderFirstPersonItem",
        cancellable = true
    )
    private void old_animations$renderFirstPersonItem(
        AbstractClientPlayerEntity player,
        float tickDelta,
        float pitch,
        Hand hand,
        float swingProgress,
        ItemStack item,
        float equipProgress,
        MatrixStack matrixStack,
        VertexConsumerProvider vertexConsumers,
        int light,
        CallbackInfo callbackInfo
    ) {
        if (ItemRescalingFeature.isEnabled()) {
            ItemRenderingFeatureExecutionContext context = ItemRenderingFeatureExecutionContext.create(
                callbackInfo, item, hand, new ItemRenderingMatrices(vertexConsumers, matrixStack),
                new ItemRenderingProgress(swingProgress, equipProgress, tickDelta)
            );
            itemRescalingFeature.get().transform(context);
        }
        if (SwordBlockingFeature.isEnabled() && item.getItem() instanceof SwordItem && player.isUsingItem()) {
            ItemRenderingFeatureExecutionContext context = ItemRenderingFeatureExecutionContext.create(
                callbackInfo, item, hand, new ItemRenderingMatrices(vertexConsumers, matrixStack),
                new ItemRenderingProgress(swingProgress, equipProgress, tickDelta)
            );
            SwordBlockingFeature.LAZY.get().transform(context);
        }
    }

}
