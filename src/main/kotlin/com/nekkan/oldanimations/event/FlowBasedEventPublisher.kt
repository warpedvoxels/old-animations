package com.nekkan.oldanimations.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface FlowBasedEventPublisher: EventPublisher {

    val flow: Flow<Event>

}

interface SharedFlowBasedEventPublisher: FlowBasedEventPublisher {

    override val flow: SharedFlow<Event>

}
