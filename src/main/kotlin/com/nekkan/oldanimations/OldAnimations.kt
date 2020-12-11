@file:JvmName("OldAnimations")
package com.nekkan.oldanimations

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

const val OLD_ANIMATIONS_REPOSITORY = "https://github.com/nekkan/old-animations"

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

    OldAnimations.info("Old Animations is up-to-date! Latest version: 1.0-SNAPSHOT for 1.16.x $OLD_ANIMATIONS_REPOSITORY")
}

