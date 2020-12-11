package com.nekkan.oldanimations.updater

interface UpdateChecker {

    suspend fun isLatestVersion(current: String): Boolean

}
