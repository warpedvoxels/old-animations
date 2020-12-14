@file:JvmName("OldAnimations")

package com.nekkan.oldanimations

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventRedirector
import com.nekkan.oldanimations.modules.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry
import net.fabricmc.fabric.api.client.model.ModelVariantProvider
import net.minecraft.client.render.model.json.JsonUnbakedModel
import net.minecraft.item.Items
import net.minecraft.item.SwordItem
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.io.InputStreamReader

// Use separated fields to a future usage of GitHubUpdateChecker.
private const val GITHUB_BASE_URL = "https://github.com/"
private const val OLD_ANIMATIONS_REPOSITORY_OWNER = "nekkan"
private const val OLD_ANIMATIONS_REPOSITORY_ID = "old-animations"
const val OLD_ANIMATIONS_REPOSITORY = "$GITHUB_BASE_URL/$OLD_ANIMATIONS_REPOSITORY_OWNER/$OLD_ANIMATIONS_REPOSITORY_ID"

@get:JvmName("getLogger")
val OldAnimations: Logger = LogManager.getFormatterLogger("com.nekkan.oldanimations.OldAnimations")

@get:JvmName("getRedirector")
val eventRedirector = EventRedirector()

@JvmSynthetic
val coroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

fun <T: LegacyAnimation> isEnabled(javaClass: Class<T>): Boolean {
    val config = AutoConfig.getConfigHolder(OldAnimationsConfig::class.java).config
    return when {
        javaClass.isAssignableFrom(SwordBlockingAnimation::class.java) -> config.enableSwordBlockingAnimation
        javaClass.isAssignableFrom(LegacySneakAnimation::class.java) -> config.enableLegacySneakAnimation
        javaClass.isAssignableFrom(LegacyRodPosition::class.java) -> config.enableLegacyRodPosition
        javaClass.isAssignableFrom(LegacyBowPosition::class.java) -> config.enableLegacyBowPosition
        else -> error("Animation not registered.")
    }
}

fun Event.redirect() = coroutineScope.launch {
    eventRedirector.publish(this@redirect)
}

/**
 * This code will be obviously updated later. This single-line `println` code will print when the Fabric mod start up.
 * This means that this code will be run only to functionality testing purposes.
 */
fun init() {
    /**
     * Set the `kotlinx.coroutines.debug` property to `on` to allow Minecraft to output coroutine debug information.
     */
    System.setProperty("kotlinx.coroutines.debug", "on")

    OldAnimations.info("[OldAnimations] The mod has been loaded successfully!")
    loadStep("1/2", "Loading configuration...") { registerConfig() }
    loadStep("2/2", "Register identifiers...") { registerIdentifiers() }
}

private inline fun loadStep(step: String, name: String, callback: () -> Unit) {
    OldAnimations.info("[OldAnimations] $step -> $name")
    callback()
}

/**
 * Register a TOML configuration using AutoConfig.
 */
private fun registerConfig() {
    AutoConfig.register(OldAnimationsConfig::class.java) { config, `class` -> Toml4jConfigSerializer(config, `class`) }
}

/**
 * Add some identifiers to the Fabric item identifier registry.
 */
private fun registerIdentifiers() {
    // Identifier used to register the blocking models to apply the sword blocking animation.
    // This option will be only disabled when game restarts.
    if(isEnabled(SwordBlockingAnimation::class.java)) {
        ModelLoadingRegistry.INSTANCE.registerVariantProvider { manager ->
            ModelVariantProvider { modelId, _ ->
                when(Identifier(modelId.namespace, modelId.path)) {
                    Registry.ITEM.getId(Items.SHIELD) -> {
                        val resource = manager.getResource(Identifier("oldanimations", "models/new_shield.json"))
                        val reader = InputStreamReader(resource.inputStream)
                        JsonUnbakedModel.deserialize(reader)
                    }
                    else -> null
                }
            }
        }
        val identifier = Identifier("blocking")
        Registry.ITEM.forEach {
            if(it is SwordItem) {
                SwordBlockingAnimation.registerFor(it, identifier)
            }
        }
    }
}
