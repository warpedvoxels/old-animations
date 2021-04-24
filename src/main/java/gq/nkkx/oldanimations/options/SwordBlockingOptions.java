package gq.nkkx.oldanimations.options;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "sword-blocking")
public class SwordBlockingOptions implements ConfigData {

    boolean isEnabled = true;

    Mode mode = Mode.SHIELD_ONLY;

    public boolean isEnabled() {
        return isEnabled;
    }

    public enum Mode {
        EMPTY_OFFHAND,

        SHIELD_ONLY
    }

}
