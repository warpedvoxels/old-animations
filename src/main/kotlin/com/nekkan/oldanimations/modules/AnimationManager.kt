@file:Suppress("unchecked_cast", "nothing_to_inline")

package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.event.Event
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.typeOf

class AnimationManager(private val collection: MutableMap<KType, LegacyAnimation<*>>) {

    private fun <T: Event> getOrNull(type: KType): LegacyAnimation<T>? {
        return collection[type] as? LegacyAnimation<T>?
    }

    operator fun <T: Event> get(type: KType): LegacyAnimation<T> {
        return getOrNull(type) ?: error("Invalid animation or type does not match.")
    }

    operator fun set(type: KType, animation: LegacyAnimation<*>) {
        collection[type] = animation
    }

}

inline fun <T: Event> AnimationManager.get(kClass: KClass<T>) = get<T>(kClass.createType())

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T: Event> AnimationManager.get() = get<T>(typeOf<T>())

@OptIn(ExperimentalStdlibApi::class)
inline fun <reified T: Event> AnimationManager.set(animation: LegacyAnimation<T>) {
    return set(typeOf<T>(), animation)
}
