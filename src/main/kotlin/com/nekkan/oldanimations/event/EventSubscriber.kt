package com.nekkan.oldanimations.event

import kotlin.reflect.KClass

interface EventSubscriber {

    fun <T: Any> subscribe(forClass: KClass<T>, callback: (T) -> Unit)

}
