package gq.nkkx.oldanimations.options;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Category;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "old-animations")
public class OldAnimationsOptions extends PartitioningSerializer.GlobalData {

    @Category("old-sneaking")
    @Gui.CollapsibleObject
    @Gui.TransitiveObject
    OldSneakingFeatureOptions oldSneaking = new OldSneakingFeatureOptions();

    @Category("item-rescaling")
    @Gui.CollapsibleObject
    @Gui.TransitiveObject
    ItemRescalingOptions itemRescaling = new ItemRescalingOptions();

    public OldSneakingFeatureOptions getOldSneaking() {
        return oldSneaking;
    }

    public ItemRescalingOptions getRescaling() {
        return itemRescaling;
    }

}
