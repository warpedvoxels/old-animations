package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.settings.Checkbox
import com.nekkan.oldanimations.settings.Identifier

interface LegacyAnimation<T: Event, R: Any> {

    val identifier: Identifier

    val isEnabled: Checkbox

    fun disable() { /* no-op */
    }

    fun execute(event: T): R

}
