package com.nekkan.oldanimations.settings

import com.nekkan.oldanimations.modules.LegacyAnimation

data class Checkbox(
    override val name: String,
    override val description: String,
    val isCheckedByDefault: Boolean = false,
    val isChecked: Boolean = isCheckedByDefault,
    val isLocked: Boolean = false
): Setting

inline fun LegacyAnimation<*, *>.EnablingCheckbox() = Checkbox(identifier.name, identifier.description)
