package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

sealed class KeyboardEvent: Event.Pre() {

    abstract val keyCode: Int

    abstract val scanCode: Int

    abstract val action: Int

    abstract val modifiers: Int

    /**
     * A event that will be executed when a keyboard key is pressed.
     */
    open class Press(
        override val keyCode: Int,
        override val scanCode: Int,
        override val action: Int,
        override val modifiers: Int,
        override val publisher: EventPublisher,
        override val callbackInfo: CallbackInfo
    ): KeyboardEvent()

    /**
     * A event that will be executed while the keyboard key is pressed.
     */
    open class Hold(
        override val keyCode: Int,
        override val scanCode: Int,
        override val action: Int,
        override val modifiers: Int,
        override val publisher: EventPublisher,
        override val callbackInfo: CallbackInfo
    ): KeyboardEvent()

}
