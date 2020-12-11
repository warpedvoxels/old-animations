@file:JvmName("Extensions")

package com.nekkan.oldanimations

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.FlowBasedEventSubscriber
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import java.net.URL

inline fun <reified T: Event> FlowBasedEventSubscriber.onEach(noinline callback: (T) -> Unit) = flow
    .filterIsInstance<T>()
    .onEach { callback(it) }

inline fun URL.get(): String? {
    return try {
        readText(Charsets.UTF_8)
    } catch(exception: Throwable) {
        OldAnimations.error(exception)
        null
    }
}
