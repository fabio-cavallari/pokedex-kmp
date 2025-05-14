package org.example.project.domain.model

import org.example.project.data.model.PokemonDto

data class Pokemon (
    val name: String
)

fun PokemonDto.asDomainModel() = Pokemon(name)