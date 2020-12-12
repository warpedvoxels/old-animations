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

internal class AnimationManager(private val _collection: MutableMap<KType, LegacyAnimation<*, *>>) {

    val collection: Map<KType, LegacyAnimation<*, *>> get() = _collection

    private fun <T: Event, R: Any> getOrNull(type: KType): LegacyAnimation<T, R>? {
        return _collection[type] as? LegacyAnimation<T, R>?
    }

    operator fun <T: Event, R: Any> get(type: KType): LegacyAnimation<T, R> {
        return getOrNull(type) ?: error("Invalid animation or type does not match.")
    }

    inline operator fun <reified T: Event> set(type: KType, animation: LegacyAnimation<T, Any>) {
        _collection[type] = animation
        redirect(type, animation)
    }

}

private inline fun <reified T: Event> redirect(type: KType, animation: LegacyAnimation<T, Any>) {
    eventRedirector.with<T>(coroutineScope) {
        animation.execute(it)
    }
}

internal inline fun <T: Event, R: Any> AnimationManager.get(kClass: KClass<T>) = get<T, R>(kClass.createType())

@OptIn(ExperimentalStdlibApi::class)
internal inline fun <reified T: Event, R: Any> AnimationManager.get() = get<T, R>(typeOf<T>())

@OptIn(ExperimentalStdlibApi::class)
internal inline fun <reified T: Event> AnimationManager.set(animation: LegacyAnimation<T, Any>) {
    return set(typeOf<T>(), animation)
}
