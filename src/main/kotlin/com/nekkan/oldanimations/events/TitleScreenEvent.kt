package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

data class TitleScreenEvent(
    override val callbackInfo: CallbackInfo,
    override val publisher: EventPublisher,
): Event.Return()
