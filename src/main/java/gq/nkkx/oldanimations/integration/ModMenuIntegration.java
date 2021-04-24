package gq.nkkx.oldanimations.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.text.TranslatableText;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> ConfigBuilder.create()
            .setParentScreen(parentScreen)
            .setTitle(new TranslatableText("options.screen.title"))
            .build();
    }
}
