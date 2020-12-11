package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event

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
        override val modifiers: Int
    ): KeyboardEvent()

    /**
     * A event that will be executed while the keyboard key is pressed.
     */
    open class Hold(
        override val keyCode: Int,
        override val scanCode: Int,
        override val action: Int,
        override val modifiers: Int
    ): KeyboardEvent()

}
