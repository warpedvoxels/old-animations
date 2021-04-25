package gq.nkkx.oldanimations.client;

import gq.nkkx.oldanimations.options.OldAnimationsOptions;
import gq.nkkx.oldanimations.registry.ItemRescalingRegistry;
import gq.nkkx.oldanimations.utils.Lazy;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class OldAnimationsClient implements ClientModInitializer {

    private static final Lazy<OldAnimationsOptions> options = Lazy.create(() ->
        AutoConfig
            .getConfigHolder(OldAnimationsOptions.class)
            .getConfig()
    );

    public static OldAnimationsOptions options() {
        return options.get();
    }

    @Override
    public void onInitializeClient() {
        AutoConfig.register(OldAnimationsOptions.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
        ItemRescalingRegistry.init();
    }

}
