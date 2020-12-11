package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

/**
 * A event that will be executed when a keyboard key is pressed.
 */
data class KeyboardPressEvent(
    val keyCode: Int,
    val scanCode: Int,
    val action: Int,
    val modifiers: Int,
    override val publisher: EventPublisher,
    override val callbackInfo: CallbackInfo
): Event.Pre()
