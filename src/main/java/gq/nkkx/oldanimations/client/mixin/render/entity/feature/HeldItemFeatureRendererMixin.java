package gq.nkkx.oldanimations.client.mixin.render.entity.feature;

import gq.nkkx.oldanimations.features.context.ItemRenderingMatrices;
import gq.nkkx.oldanimations.renderer.OldAnimationsThirdPersonItemRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeldItemFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.ModelWithArms;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemFeatureRenderer.class)
public class HeldItemFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T> & ModelWithArms> {

    private static final OldAnimationsThirdPersonItemRenderer renderer = new OldAnimationsThirdPersonItemRenderer();

    @Inject(method = "renderItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;" +
            "push()V"), cancellable = true)
    private void old_animations$renderItem(
        LivingEntity entity, ItemStack stack, ModelTransformation.Mode transformationMode, Arm arm, MatrixStack matrices,
        VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci
    ) {
        renderer.transformThirdPersonItem(entity, stack,
                new ItemRenderingMatrices(vertexConsumers,
                matrices), ci);
    }

}
