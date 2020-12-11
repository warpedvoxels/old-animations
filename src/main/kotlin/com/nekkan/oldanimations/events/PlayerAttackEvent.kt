package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

abstract class PlayerAttackEvent {

    open class Pre(
        val attacker: PlayerEntity,
        val attacked: LivingEntity,
        override val publisher: EventPublisher,
        override val callbackInfo: CallbackInfo
    ): Event.Pre()

    open class Post(
        val attacker: PlayerEntity,
        val attacked: LivingEntity,
        override val publisher: EventPublisher,
        override val callbackInfo: CallbackInfo
    ): Event.Post()

}
