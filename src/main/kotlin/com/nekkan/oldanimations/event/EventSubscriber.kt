package com.nekkan.oldanimations.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlin.reflect.KClass

interface EventSubscriber {

    fun <T: Any> subscribe(forClass: KClass<T>, callback: (T) -> Unit)

}

interface FlowBasedEventSubscriber: EventSubscriber {

    val flow: Flow<Event>

}

interface SharedFlowBasedEventSubscriber: FlowBasedEventSubscriber {

    override val flow: SharedFlow<Event>

}
