package com.nekkan.oldanimations.settings

data class Identifier(
    override val name: String,
    val id: String,
    override val description: String
): Setting

inline fun Any.Identifier(id: String, description: String) = Identifier(
    this::class.simpleName!!, id, description
)
