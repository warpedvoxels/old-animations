package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.settings.EnablingCheckbox
import com.nekkan.oldanimations.settings.Identifier
import com.nekkan.oldanimations.settings.ScreenSetting

class LegacySneakAnimation: LegacyAnimation<Event, Unit> {

    override val identifier = Identifier("Remove the smooth sneaking animation from newer versions.")

    @ScreenSetting(isDefault = true)
    override val isEnabled = EnablingCheckbox()

    /**
     * Replaced by [com.nekkan.oldanimations.mixins.MixinLivingEntity].
     */
    override fun execute(event: Event) {
        /* no-op */
    }

}
