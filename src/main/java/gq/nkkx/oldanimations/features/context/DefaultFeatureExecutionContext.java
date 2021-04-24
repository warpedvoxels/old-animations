package gq.nkkx.oldanimations.features.context;

import gq.nkkx.oldanimations.options.OldAnimationsOptions;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class DefaultFeatureExecutionContext implements FeatureExecutionContext {

    private final PlayerEntity player;
    private final OldAnimationsOptions options;
    private final CallbackInfo callbackInfo;

    public DefaultFeatureExecutionContext(
        PlayerEntity player, OldAnimationsOptions options, CallbackInfo callbackInfo
    ) {
        this.player = player;
        this.options = options;
        this.callbackInfo = callbackInfo;
    }

    @Override
    public PlayerEntity player() {
        return player;
    }

    @Override
    public OldAnimationsOptions options() {
        return options;
    }

    @Override
    public CallbackInfo callbackInfo() {
        return callbackInfo;
    }

}
