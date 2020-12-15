@file:JvmName("Extensions")

package com.nekkan.oldanimations

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

suspend inline fun URL.get() = withContext(Dispatchers.IO) {
    readText(Charsets.UTF_8)
}
