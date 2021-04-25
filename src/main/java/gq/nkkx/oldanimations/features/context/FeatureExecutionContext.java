package gq.nkkx.oldanimations.features.context;

import gq.nkkx.oldanimations.client.OldAnimationsClient;
import gq.nkkx.oldanimations.features.OldAnimationsFeature;
import gq.nkkx.oldanimations.options.OldAnimationsOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * The context needed to perform a transformation on a feature.
 *
 * @see OldAnimationsFeature
 * @since 2.0.0-SNAPSHOT
 */
public interface FeatureExecutionContext {

    static FeatureExecutionContext create(PlayerEntity player, OldAnimationsOptions options, CallbackInfo callbackInfo) {
        return new DefaultFeatureExecutionContext(player, options, callbackInfo);
    }

    static FeatureExecutionContext create(PlayerEntity playerEntity, CallbackInfo callbackInfo) {
        return create(playerEntity, OldAnimationsClient.options(), callbackInfo);
    }

    static FeatureExecutionContext create(CallbackInfo callbackInfo) {
        return create(MinecraftClient.getInstance().player, OldAnimationsClient.options(), callbackInfo);
    }

    PlayerEntity player();

    OldAnimationsOptions options();

    CallbackInfo callbackInfo();

}
