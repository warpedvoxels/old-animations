package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.event.Event;
import com.nekkan.oldanimations.event.EventPublisher;
import com.nekkan.oldanimations.events.KeyboardPressEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {

    @Inject(at = @At("HEAD"), method = "onKey")
    private void preProcessKey(long window, int keyCode, int scanCode, int action, int modifiers, CallbackInfo callbackInfo) {
        EventPublisher publisher = OldAnimations.getRedirector();
        Event event = new KeyboardPressEvent(keyCode, scanCode, action, modifiers, publisher, callbackInfo);
        OldAnimations.redirect(event);
    }

}
