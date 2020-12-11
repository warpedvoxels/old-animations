package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event

sealed class MouseEvent {

    /**
     * An event that will be executed when the scroll (middle key) be pressed.
     */
    object Middle: Event.Pre()

    /**
     * An event that will be executed when the left click be pressed.
     */
    object Left: Event.Pre()

    /**
     * An event that will be executed when the right click be pressed.
     */
    object Right: Event.Pre()

}
