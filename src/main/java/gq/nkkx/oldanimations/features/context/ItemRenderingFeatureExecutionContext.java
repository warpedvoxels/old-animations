package gq.nkkx.oldanimations.features.context;

import gq.nkkx.oldanimations.client.OldAnimationsClient;
import gq.nkkx.oldanimations.options.OldAnimationsOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

public interface ItemRenderingFeatureExecutionContext extends FeatureExecutionContext {

    static ItemRenderingFeatureExecutionContext create(CallbackInfo callbackInfo, PlayerEntity player,
                                                       OldAnimationsOptions options, ItemStack item,
                                                       Hand hand, ItemRenderingMatrices matrices,
                                                       ItemRenderingProgress progress
    ) {
        return new DefaultItemRenderingFeatureExecutionContext(callbackInfo, player, options, item, hand, matrices, progress);
    }

    static ItemRenderingFeatureExecutionContext create(CallbackInfo callbackInfo, ItemStack item, Hand hand,
                                                       ItemRenderingMatrices matrices, ItemRenderingProgress progress
    ) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        OldAnimationsOptions options = OldAnimationsClient.options();
        return new DefaultItemRenderingFeatureExecutionContext(callbackInfo, player, options, item, hand, matrices, progress);
    }

    ItemStack itemStack();

    Hand hand();

    ItemRenderingMatrices matrices();

    Optional<ItemRenderingProgress> progress();

}
