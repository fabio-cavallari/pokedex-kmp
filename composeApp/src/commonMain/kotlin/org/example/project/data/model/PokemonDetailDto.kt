package org.example.project.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailDto(
    @SerialName("sprites") val sprite: SpriteDto,
    val types: List<TypeContainerDto>
)