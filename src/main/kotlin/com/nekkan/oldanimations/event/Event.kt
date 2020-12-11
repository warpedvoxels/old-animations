package com.nekkan.oldanimations.event

sealed class Event {

    /**
     * Executes before a call (HEAD) of a Mixin injection.
     */
    abstract class Pre: Event()

    /**
     * Execute after a call (INVOKE) of a Mixin injection.
     */
    abstract class Post: Event()

}
