package com.nekkan.oldanimations.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface FlowBasedEventHandler: EventHandler {

    val flow: Flow<Event>

}

interface SharedFlowBasedEventHandler: FlowBasedEventHandler {

    override val flow: SharedFlow<Event>

}
