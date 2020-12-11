package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.event.EventPublisher;
import com.nekkan.oldanimations.events.MouseEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MixinMouse {

    @Inject(at = @At("INVOKE"), method = "onMouseButton")
    private void preProcessKey(long window, int button, int action, int mods, CallbackInfo callbackInfo) {
        EventPublisher publisher = OldAnimations.getRedirector();
        AccessorMouse accessor = (AccessorMouse) MinecraftClient.getInstance().mouse;
        if(accessor.isLeftButtonClicked()) {
            OldAnimations.redirect(new MouseEvent.Left(publisher, callbackInfo));
            return;
        }
        if(accessor.isRightButtonClicked()) {
            OldAnimations.redirect(new MouseEvent.Right(publisher, callbackInfo));
            return;
        }
        if(accessor.isRightButtonClicked()) {
            OldAnimations.redirect(new MouseEvent.Middle(publisher, callbackInfo));
        }
    }

}
