package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.settings.Identifier

interface LegacyAnimation<T: Event> {

    val identifier: Identifier

    val isEnabled: Boolean

    fun disable() { /* no-op */
    }

    fun update(event: T)

}
