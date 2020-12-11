package com.nekkan.oldanimations.updater

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubUpdateChecker(repositoryOwner: String, repositoryId: String): UpdateChecker {

    companion object {
        private const val BASE_URL = "https://api.github.com/repos/%s/%s"
        private const val RELEASE_URL_SUFFIX = "/releases/%s"
    }

    private val url = BASE_URL.format(repositoryOwner, repositoryId)

    override suspend fun isLatestVersion(current: String): Boolean = withContext(Dispatchers.IO) {
        TODO("")
    }

}
