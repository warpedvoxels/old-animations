package com.nekkan.oldanimations.event

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

sealed class Event {

    abstract val publisher: EventPublisher

    abstract val callbackInfo: CallbackInfo

    /**
     * Executes before a call (HEAD) of a Mixin injection.
     */
    abstract class Pre: Event()

    /**
     * Execute after a call (INVOKE) of a Mixin injection.
     */
    abstract class Post: Event()

}
