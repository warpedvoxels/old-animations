package com.nekkan.oldanimations.event

interface EventHandler {

    suspend fun executeFor(event: Event)

}
