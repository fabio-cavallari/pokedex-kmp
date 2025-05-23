package org.example.project.data.repository

import org.example.project.data.util.ResultState
import org.example.project.domain.model.Pokemon

interface PokemonListRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): ResultState<List<Pokemon>>
}