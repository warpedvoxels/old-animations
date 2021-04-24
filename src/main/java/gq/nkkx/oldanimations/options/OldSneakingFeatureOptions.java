package gq.nkkx.oldanimations.options;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "old-sneaking")
public class OldSneakingFeatureOptions implements ConfigData {

    boolean isEnabled = true;

    public boolean isEnabled() {
        return isEnabled;
    }

}
