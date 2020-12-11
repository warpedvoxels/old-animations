@file:JvmName("Extensions")

package com.nekkan.oldanimations

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.FlowBasedEventSubscriber
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import java.net.URL

inline fun <reified T: Event> FlowBasedEventSubscriber.onEach(noinline callback: (T) -> Unit) = flow
    .filterIsInstance<T>()
    .onEach { callback(it) }

suspend inline fun URL.get() = withContext(Dispatchers.IO) {
    readText(Charsets.UTF_8)
}
