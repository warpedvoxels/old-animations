package gq.nkkx.oldanimations.features.context;

import gq.nkkx.oldanimations.options.OldAnimationsOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

public interface ItemRenderingFeatureExecutionContext extends FeatureExecutionContext {

    static ItemRenderingFeatureExecutionContext create(CallbackInfo callbackInfo, PlayerEntity player,
                                                       OldAnimationsOptions options, int light, ItemStack item,
                                                       Hand hand, ItemRenderingMatrices matrices,
                                                       ItemRenderingProgress progress
    ) {
        return new DefaultItemRenderingFeatureExecutionContext(callbackInfo, player, options, light, item, hand, matrices, progress);
    }

    int lightLevel();

    ItemStack item();

    Hand hand();

    ItemRenderingMatrices matrices();

    Optional<ItemRenderingProgress> progress();

}
