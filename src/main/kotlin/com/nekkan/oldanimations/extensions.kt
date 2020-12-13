@file:JvmName("Extensions")

package com.nekkan.oldanimations

import com.mojang.brigadier.context.CommandContext
import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.FlowBasedEventSubscriber
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import net.minecraft.client.MinecraftClient
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.server.command.ServerCommandSource
import net.minecraft.text.LiteralText
import java.net.URL

inline fun <reified T: Event> FlowBasedEventSubscriber.onEach(crossinline callback: (T) -> Unit) = flow
    .filterIsInstance<T>()
    .onEach { callback(it) }

inline fun <reified T: Event> FlowBasedEventSubscriber.with(
    coroutineScope: CoroutineScope,
    crossinline callback: (T) -> Unit
) = onEach<T>(callback).launchIn(coroutineScope)

internal inline val clientPlayerEntity: ClientPlayerEntity
    get() = MinecraftClient.getInstance().player ?: error("Cannot get the client player in their current state.")

suspend inline fun URL.get() = withContext(Dispatchers.IO) {
    readText(Charsets.UTF_8)
}

inline fun CommandContext<ServerCommandSource>.feedback(text: String) = source.sendFeedback(LiteralText(text), false)

typealias MinecraftIdentifier = net.minecraft.util.Identifier
