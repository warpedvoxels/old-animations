package gq.nkkx.oldanimations.features.context;

import gq.nkkx.oldanimations.options.OldAnimationsOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

public class DefaultItemRenderingFeatureExecutionContext implements ItemRenderingFeatureExecutionContext {

    private final CallbackInfo callbackInfo;
    private final PlayerEntity player;
    private final OldAnimationsOptions options;
    private final int light;
    private final ItemStack item;
    private final Hand hand;
    private final ItemRenderingMatrices matrices;
    private final ItemRenderingProgress progress;

    public DefaultItemRenderingFeatureExecutionContext(
        CallbackInfo callbackInfo, PlayerEntity player, OldAnimationsOptions options, int light, ItemStack item,
        Hand hand, ItemRenderingMatrices matrices, ItemRenderingProgress progress
    ) {
        this.callbackInfo = callbackInfo;
        this.light = light;
        this.item = item;
        this.hand = hand;
        this.matrices = matrices;
        this.progress = progress;
        this.player = player;
        this.options = options;
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

    @Override
    public int lightLevel() {
        return light;
    }

    @Override
    public ItemStack itemStack() {
        return item;
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public ItemRenderingMatrices matrices() {
        return matrices;
    }

    @Override
    public Optional<ItemRenderingProgress> progress() {
        return Optional.ofNullable(progress);
    }

}
