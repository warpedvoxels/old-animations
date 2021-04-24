package gq.nkkx.oldanimations;

import gq.nkkx.oldanimations.options.OldAnimationsOptions;
import gq.nkkx.oldanimations.registry.ItemRescalingRegistry;
import gq.nkkx.oldanimations.utils.Lazy;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class OldAnimations implements ModInitializer {

    private static final Lazy<OldAnimationsOptions> options = Lazy.create(() ->
        AutoConfig
            .getConfigHolder(OldAnimationsOptions.class)
            .getConfig()
    );

    public static Identifier locate(String path) {
        return new Identifier("old-animations", path);
    }

    public static OldAnimationsOptions options() {
        return options.get();
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(OldAnimationsOptions.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        ItemRescalingRegistry.init();
    }

}
