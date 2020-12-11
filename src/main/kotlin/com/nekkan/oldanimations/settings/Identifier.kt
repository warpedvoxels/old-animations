package com.nekkan.oldanimations.settings

data class Identifier(
    override val name: String,
    override val id: String,
    override val description: String
): Setting

inline fun <reified T: Any> T.Identifier(id: String, description: String) = Identifier(
    this::class.simpleName!!, id, description
)
