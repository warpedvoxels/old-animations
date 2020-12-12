package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.settings.EnablingCheckbox
import com.nekkan.oldanimations.settings.Identifier
import com.nekkan.oldanimations.settings.ScreenSetting

class LegacySneakAnimation: LegacyAnimation {

    override val identifier = Identifier("sneaking", "Remove the smooth sneaking animation from newer versions.")

    @ScreenSetting(isDefault = true)
    override val isEnabled = EnablingCheckbox()

}
