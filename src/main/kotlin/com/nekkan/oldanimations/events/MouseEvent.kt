package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

sealed class MouseEvent: Event.Pre() {

    /**
     * An event that will be executed when the scroll (middle key) be pressed.
     */
    data class Middle(
        override val publisher: EventPublisher,
        override val callbackInfo: CallbackInfo
    ): MouseEvent()

    /**
     * An event that will be executed when the left click be pressed.
     */
    data class Left(
        override val publisher: EventPublisher,
        override val callbackInfo: CallbackInfo
    ): MouseEvent()

    /**
     * An event that will be executed when the right click be pressed.
     */
    data class Right(
        override val publisher: EventPublisher,
        override val callbackInfo: CallbackInfo
    ): MouseEvent()

}
