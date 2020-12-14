package com.nekkan.oldanimations;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "oldanimations")
public class OldAnimationsConfig implements ConfigData {

    @Comment("Backport the bow position from 1.7.x version to newer ones.")
    boolean enableLegacyBowPosition = true;

    @Comment("Backport the rod position from 1.7.x version to newer ones.")
    boolean enableLegacyRodPosition = true;

    @Comment("Remove the smooth sneaking animation from newer versions.")
    boolean enableLegacySneakAnimation = true;

    @Comment("Restore the sword blocking animation when using a shield.")
    @ConfigEntry.Gui.RequiresRestart
    boolean enableSwordBlockingAnimation = true;

}
