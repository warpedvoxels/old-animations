package gq.nkkx.oldanimations.renderer;

import gq.nkkx.oldanimations.features.ItemRescalingFeature;
import gq.nkkx.oldanimations.features.SwordBlockingFeature;
import gq.nkkx.oldanimations.features.context.ItemRenderingFeatureExecutionContext;
import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import gq.nkkx.oldanimations.features.context.ItemRenderingProgress;
import gq.nkkx.oldanimations.utils.Lazy;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class OldAnimationsHeldItemRenderer {

    private static final Lazy<ItemRescalingFeature> itemRescalingFeature = Lazy.create(ItemRescalingFeature::new);

    public void transformRender(
        AbstractClientPlayerEntity player,
        float tickDelta,
        float pitch,
        Hand hand,
        float swingProgress,
        ItemStack item,
        float equipProgress,
        MatrixStack matrixStack,
        VertexConsumerProvider vertexConsumers,
        CallbackInfo callbackInfo
    ) {
        if (SwordBlockingFeature.isEnabled()) {
            SwordBlockingFeature feature = SwordBlockingFeature.LAZY.get();
            if(feature.shouldHideItem(player, hand)) {
                callbackInfo.cancel();
                return;
            }

            ItemRenderingFeatureExecutionContext context = ItemRenderingFeatureExecutionContext.create(
                    callbackInfo, item, hand, new ItemRenderingMatrices(vertexConsumers, matrixStack),
                    new ItemRenderingProgress(swingProgress, equipProgress, tickDelta)
            );
            feature.transform(context);
        }
        if (ItemRescalingFeature.isEnabled()) {
            ItemRenderingFeatureExecutionContext context = ItemRenderingFeatureExecutionContext.create(
                callbackInfo, item, hand, new ItemRenderingMatrices(vertexConsumers, matrixStack),
                new ItemRenderingProgress(swingProgress, equipProgress, tickDelta)
            );
            itemRescalingFeature.get().transform(context);
        }
    }

}
