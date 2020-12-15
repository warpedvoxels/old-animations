package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.modules.LegacyPlayerList;
import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListHud.class)
public class MixinPlayerListHud {

    @Shadow
    private Text footer;

    @Shadow
    private Text header;

    @Inject(at = @At("HEAD"), method = "tick")
    private void processTick(CallbackInfo callbackInfo) {
        if(OldAnimations.isEnabled(LegacyPlayerList.class)) {
            footer = null;
            header = null;
        }
    }

}
