package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.settings.Checkbox
import com.nekkan.oldanimations.settings.Identifier
import com.nekkan.oldanimations.settings.ScreenSetting

interface LegacyAnimation {

    val identifier: Identifier

    @ScreenSetting(isDefault = true)
    val isEnabled: Checkbox

    fun disable() {
        /* no-op */
    }

}
