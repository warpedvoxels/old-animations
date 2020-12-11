package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

data class ToggleSneakingEvent(
    val isSneaking: Boolean,
    override val publisher: EventPublisher,
    override val callbackInfo: CallbackInfo
): Event.Pre()
