package gq.nkkx.oldanimations.features;

import gq.nkkx.oldanimations.client.OldAnimationsClient;
import gq.nkkx.oldanimations.features.context.FeatureExecutionContext;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

public class CenteredInventoryFeature implements OldAnimationsFeature<FeatureExecutionContext> {

    private final InventoryScreen parent;

    public CenteredInventoryFeature(InventoryScreen parent) {
        this.parent = parent;
    }

    public static boolean isEnabled() {
        return OldAnimationsClient.options().getCenteredInventory().isEnabled();
    }

    @Override
    public void transform(FeatureExecutionContext context) {
        if (!context.player().getActiveStatusEffects().isEmpty()) {
            parent.setZOffset(parent.width / 2);
        }
    }

}
