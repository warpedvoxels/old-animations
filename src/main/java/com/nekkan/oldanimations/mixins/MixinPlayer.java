package com.nekkan.oldanimations.mixins;

import com.nekkan.oldanimations.OldAnimations;
import com.nekkan.oldanimations.event.EventPublisher;
import com.nekkan.oldanimations.events.PrePlayerAttackEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class MixinPlayer {

    private PlayerEntity getPlayer() {
        return MinecraftClient.getInstance().player;
    }

    @Inject(at = @At("HEAD"), method = "attack")
    private void processAttack(Entity target, CallbackInfo callbackInfo) {
        if(!callbackInfo.isCancelled()) {
            EventPublisher publisher = OldAnimations.getRedirector();
            LivingEntity entity = (LivingEntity) target;
            OldAnimations.redirect(new PrePlayerAttackEvent(getPlayer(), entity, publisher, callbackInfo));
        }
    }

}
