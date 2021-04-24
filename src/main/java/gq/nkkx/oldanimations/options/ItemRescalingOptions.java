package gq.nkkx.oldanimations.options;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "item-rescaling")
public class ItemRescalingOptions implements ConfigData {

    boolean isEnabled = true;

    public boolean isEnabled() {
        return isEnabled;
    }

}
