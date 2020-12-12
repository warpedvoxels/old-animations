@file:Suppress("unchecked_cast", "nothing_to_inline")

package com.nekkan.oldanimations.modules

import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.typeOf

internal class AnimationManager(private val _collection: MutableMap<KType, LegacyAnimation>) {

    val collection: Map<KType, LegacyAnimation> get() = _collection

    private fun getOrNull(type: KType) = _collection[type]

    operator fun get(type: KType): LegacyAnimation {
        return getOrNull(type) ?: error("Invalid animation or type does not match.")
    }

    operator fun set(type: KType, animation: LegacyAnimation?) {
        if(animation == null) {
            _collection.remove(type)
            return
        }
        _collection[type] = animation
    }

}

internal inline fun <T: LegacyAnimation> AnimationManager.get(kClass: KClass<T>) = get(kClass.createType())

@OptIn(ExperimentalStdlibApi::class)
internal inline fun <reified T: LegacyAnimation> AnimationManager.set(animation: T?) {
    return set(typeOf<T>(), animation)
}
