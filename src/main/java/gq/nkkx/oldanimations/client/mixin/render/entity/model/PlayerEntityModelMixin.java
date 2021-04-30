package gq.nkkx.oldanimations.client.mixin.render.entity.model;

import gq.nkkx.oldanimations.renderer.OldAnimationsThirdPersonModelRenderer;
import gq.nkkx.oldanimations.utils.PlayerEntityModelAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityModel.class)
public abstract class PlayerEntityModelMixin<T extends LivingEntity> implements PlayerEntityModelAccess {

    private static final OldAnimationsThirdPersonModelRenderer renderer = new OldAnimationsThirdPersonModelRenderer();

    @Accessor("rightSleeve")
    public abstract ModelPart getRightSleeve();

    @Accessor("leftSleeve")
    public abstract ModelPart getLeftSleeve();

    @Inject(method = "setAngles", at = @At(value = "TAIL"))
    private void old_animations$setAngles(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo callbackInfo) {
        renderer.transformThirdPersonModel(livingEntity, this, h);
    }

}
