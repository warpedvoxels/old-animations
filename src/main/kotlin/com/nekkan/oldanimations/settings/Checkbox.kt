package com.nekkan.oldanimations.settings

data class Checkbox(
    override val name: String,
    override val description: String,
    val isCheckedByDefault: Boolean = false,
    val isChecked: Boolean = isCheckedByDefault,
    val isLocked: Boolean = false
): Setting
