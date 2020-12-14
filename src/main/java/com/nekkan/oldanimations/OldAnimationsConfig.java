package com.nekkan.oldanimations;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "oldanimations")
public class OldAnimationsConfig implements ConfigData {

    @Comment
    boolean enableLegacyBowPosition = true;

    boolean enableLegacyRodPosition = true;

    boolean enableLegacySneakAnimation = true;

    @ConfigEntry.Gui.RequiresRestart
    boolean enableSwordBlockingAnimation = true;

}
