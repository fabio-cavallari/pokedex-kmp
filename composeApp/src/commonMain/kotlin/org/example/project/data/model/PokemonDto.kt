package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val name: String,
    val url: String
)