package com.nekkan.oldanimations.settings

abstract class AbstractSetting: Setting {

    override val name: String
        get() = this::class.simpleName ?: error("Invalid class.")

}
