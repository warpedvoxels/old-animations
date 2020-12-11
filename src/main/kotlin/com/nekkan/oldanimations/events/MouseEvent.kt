package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event

sealed class MouseEvent: Event.Pre() {

    /**
     * An event that will be executed when the scroll (middle key) be pressed.
     */
    object Middle: MouseEvent()

    /**
     * An event that will be executed when the left click be pressed.
     */
    object Left: MouseEvent()

    /**
     * An event that will be executed when the right click be pressed.
     */
    object Right: MouseEvent()

}
