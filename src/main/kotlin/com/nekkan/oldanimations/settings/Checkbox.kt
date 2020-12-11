package com.nekkan.oldanimations.settings

interface Checkbox: Setting {

    val isChecked: Boolean

    val isCheckedByDefault: Boolean

    val isLocked: Boolean

}
