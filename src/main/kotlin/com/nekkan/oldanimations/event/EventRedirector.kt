package com.nekkan.oldanimations.event

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlin.reflect.KClass

class EventRedirector: SharedFlowBasedEventPublisher, SharedFlowBasedEventSubscriber {

    private val _flow = MutableSharedFlow<Event>()

    override val flow: SharedFlow<Event>
        get() = _flow

    override suspend fun publish(event: Event) {
        return _flow.emit(event)
    }

    override fun <T: Event> subscribe(forClass: KClass<T>, callback: (T) -> Unit) {
        flow.filter { forClass.isInstance(it) }.onEach { callback(it as T) }
    }

}
