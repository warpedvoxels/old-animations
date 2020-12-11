package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event

sealed class KeyboardEvent {

    /**
     * A event that will be executed after a keyboard key be pressed.
     */
    object Press: Event.Post()

    /**
     * A event that will be executed while the keyboard key is pressed.
     */
    object Hold: Event.Pre()

}
