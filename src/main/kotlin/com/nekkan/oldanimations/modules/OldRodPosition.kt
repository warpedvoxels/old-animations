package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.settings.EnablingCheckbox
import com.nekkan.oldanimations.settings.Identifier
import com.nekkan.oldanimations.settings.ScreenSetting

class OldRodPosition: LegacyAnimation {

    override val identifier = Identifier("rodposition", "Backport the rod position from 1.7.x version to newer ones.")

    @ScreenSetting(isDefault = true)
    override val isEnabled = EnablingCheckbox()

}
