package com.nekkan.oldanimations.settings

data class Identifier(
    override val name: String,
    override val description: String
): Setting

inline fun Any.Identifier(description: String) = Identifier(
    this::class.simpleName!!, description
)
