package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher

sealed class MouseEvent: Event.Pre() {

    /**
     * An event that will be executed when the scroll (middle key) be pressed.
     */
    data class Middle(override val publisher: EventPublisher): MouseEvent()

    /**
     * An event that will be executed when the left click be pressed.
     */
    data class Left(override val publisher: EventPublisher): MouseEvent()

    /**
     * An event that will be executed when the right click be pressed.
     */
    data class Right(override val publisher: EventPublisher): MouseEvent()

}
