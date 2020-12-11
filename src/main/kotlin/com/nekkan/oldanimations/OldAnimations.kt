@file:JvmName("OldAnimations")

package com.nekkan.oldanimations

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

// Use separated fields to a future usage of GitHubUpdateChecker.
private const val GITHUB_BASE_URL = "https://github.com/"
private const val OLD_ANIMATIONS_REPOSITORY_OWNER = "nekkan"
private const val OLD_ANIMATIONS_REPOSITORY_ID = "old-animations"
const val OLD_ANIMATIONS_REPOSITORY = "$GITHUB_BASE_URL/$OLD_ANIMATIONS_REPOSITORY_OWNER/$OLD_ANIMATIONS_REPOSITORY_ID"

@get:JvmName("getLogger")
val OldAnimations: Logger = LogManager.getFormatterLogger("com.nekkan.oldanimations.OldAnimations")

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
}
