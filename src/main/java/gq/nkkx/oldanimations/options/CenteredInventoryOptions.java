package gq.nkkx.oldanimations.options;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "centered-inventory")
public class CenteredInventoryOptions implements ConfigData {

    boolean isEnabled = true;

    public boolean isEnabled() {
        return isEnabled;
    }

}
