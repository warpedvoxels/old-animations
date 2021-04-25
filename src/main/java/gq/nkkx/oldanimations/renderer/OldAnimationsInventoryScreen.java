package gq.nkkx.oldanimations.renderer;

import gq.nkkx.oldanimations.features.CenteredInventoryFeature;
import gq.nkkx.oldanimations.features.context.FeatureExecutionContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class OldAnimationsInventoryScreen {

    private final CenteredInventoryFeature feature;

    public OldAnimationsInventoryScreen(InventoryScreen parent) {
        this.feature = new CenteredInventoryFeature(parent);
    }

    public void render(CallbackInfo callbackInfo) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null && CenteredInventoryFeature.isEnabled() && !player.getActiveStatusEffects().isEmpty()) {
            FeatureExecutionContext context = FeatureExecutionContext.create(callbackInfo);
            feature.transform(context);
        }
    }

}
