@file:JvmName("OldAnimations")

package com.nekkan.oldanimations

import com.nekkan.oldanimations.event.Event
import com.nekkan.oldanimations.event.EventRedirector
import com.nekkan.oldanimations.modules.AnimationManager
import com.nekkan.oldanimations.modules.LegacyAnimation
import com.nekkan.oldanimations.modules.LegacySneakAnimation
import com.nekkan.oldanimations.modules.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.concurrent.ConcurrentHashMap

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
internal val animationManager = AnimationManager(ConcurrentHashMap())

fun <T: LegacyAnimation<*, R>, R: Any> isEnabled(javaClass: Class<T>): Boolean {
    return animationManager.collection.values.firstOrNull { javaClass.isInstance(it) } != null
}

fun v() {
    isEnabled(LegacySneakAnimation::class.java)
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
    loadStep("1/2", "Loading modules...") { registerModules() }
}

private inline fun loadStep(step: String, name: String, callback: () -> Unit) {
    OldAnimations.info("[OldAnimations] $step -> $name")
    callback()
}

/**
 * Add all modules to the module list.
 */
@OptIn(ExperimentalStdlibApi::class)
private fun registerModules() = with(animationManager) {
    set(LegacySneakAnimation())
}
