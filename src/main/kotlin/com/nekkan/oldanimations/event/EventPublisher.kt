package com.nekkan.oldanimations.event

interface EventPublisher {

    suspend fun executeFor(event: Event)

}
