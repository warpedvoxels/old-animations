package gq.nkkx.oldanimations.options;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "old-sneaking")
public class OldSneakingFeatureOptions implements ConfigData {

    boolean isEnabled = true;

    Mode mode = Mode.JUST_REMOVE_SMOOTHING;

    public boolean isEnabled() {
        return isEnabled;
    }

    public Mode mode() {
        return mode;
    }

    public enum Mode {
        EYE_HEIGHT_CHANGE, JUST_REMOVE_SMOOTHING
    }

}
