package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.event.EventPublisher;
import com.nekkan.oldanimations.events.ToggleSneakEvent;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class MixinEntity {

    @Inject(at = @At("HEAD"), method = "setSneaking")
    private void setSneaking(boolean sneaking, CallbackInfo callbackInfo) {
        EventPublisher publisher = OldAnimations.getRedirector();
        OldAnimations.redirect(new ToggleSneakEvent(sneaking, publisher, callbackInfo));
    }

}
