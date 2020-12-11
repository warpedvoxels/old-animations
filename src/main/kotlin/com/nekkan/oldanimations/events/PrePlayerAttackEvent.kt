package com.nekkan.oldanimations.events

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventPublisher
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo

data class PrePlayerAttackEvent(
    val attacker: PlayerEntity,
    val attacked: LivingEntity,
    override val publisher: EventPublisher,
    override val callbackInfo: CallbackInfo
): Event.Pre()
