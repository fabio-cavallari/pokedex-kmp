package org.example.project.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PokedexPageDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonDto>
)