package org.example.project.data.client

import org.example.project.data.model.PokedexPageDto
import org.example.project.data.model.PokemonDetailDto
import org.example.project.data.util.NetworkError
import org.example.project.data.util.Result

interface PokeApiClient {
    suspend fun getPokedexPage(limit: Int, offset: Int): Result<PokedexPageDto, NetworkError>
    suspend fun getPokemonDetail(pokemonUrl: String): Result<PokemonDetailDto, NetworkError>
}