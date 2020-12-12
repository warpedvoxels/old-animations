@file:Suppress("unchecked_cast", "nothing_to_inline")

package com.nekkan.oldanimations.modules

import com.nekkan.oldanimations.coroutineScope
import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.eventRedirector
import com.nekkan.oldanimations.with
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.createType
import kotlin.reflect.typeOf

internal class AnimationManager(private val collection: MutableMap<KType, LegacyAnimation<*>>) {

    private fun <T: Event> getOrNull(type: KType): LegacyAnimation<T>? {
        return collection[type] as? LegacyAnimation<T>?
    }

    operator fun <T: Event> get(type: KType): LegacyAnimation<T> {
        return getOrNull(type) ?: error("Invalid animation or type does not match.")
    }

    inline operator fun <reified T: Event> set(type: KType, animation: LegacyAnimation<T>) {
        collection[type] = animation
        redirect(type, animation)
    }

}

private inline fun <reified T: Event> redirect(type: KType, animation: LegacyAnimation<T>) {
    eventRedirector.with<T>(coroutineScope) {
        animation.update(it)
    }
}

internal inline fun <T: Event> AnimationManager.get(kClass: KClass<T>) = get<T>(kClass.createType())

@OptIn(ExperimentalStdlibApi::class)
internal inline fun <reified T: Event> AnimationManager.get() = get<T>(typeOf<T>())

@OptIn(ExperimentalStdlibApi::class)
internal inline fun <reified T: Event> AnimationManager.set(animation: LegacyAnimation<T>) {
    return set(typeOf<T>(), animation)
}
