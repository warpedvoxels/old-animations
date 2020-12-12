package com.nekkan.oldanimations

object CompilerArgs {

    const val X_OPT_IN = "-Xopt-in=kotlin.RequiresOptIn"

    fun toList(): List<String> {
        return listOf(X_OPT_IN)
    }

}
