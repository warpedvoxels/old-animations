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

    private fun <T: Event> getOrNull(type: KType): LegacyAnimation<T, Any>? {
        return _collection[type] as? LegacyAnimation<T, Any>?
    }

    operator fun <T: Event> get(type: KType): LegacyAnimation<T, Any> {
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

internal inline fun <T: Event> AnimationManager.get(kClass: KClass<T>) = get<T>(kClass.createType())

@OptIn(ExperimentalStdlibApi::class)
internal inline fun <reified T: Event> AnimationManager.get() = get<T>(typeOf<T>())

@OptIn(ExperimentalStdlibApi::class)
internal inline fun <reified T: Event> AnimationManager.set(animation: LegacyAnimation<T, Any>) {
    return set(typeOf<T>(), animation)
}
