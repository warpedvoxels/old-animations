package com.nekkan.oldanimations.event

interface EventPublisher {

    suspend fun publish(event: Event)

}
