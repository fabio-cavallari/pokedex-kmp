package org.example.project.data.repository

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import org.example.project.data.client.PokeApiClientImpl
import org.example.project.data.model.PokemonDto
import org.example.project.data.util.NetworkError
import org.example.project.data.util.Result
import org.example.project.data.util.ResultState
import org.example.project.data.util.onError
import org.example.project.data.util.onSuccess
import org.example.project.domain.model.Pokemon
import org.example.project.domain.model.asDomainModel

class PokemonListRepositoryImpl(
    private val client: PokeApiClientImpl
) : PokemonListRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): ResultState<List<Pokemon>> {
        try {
            return coroutineScope {
                val pokemonDtoList: MutableList<PokemonDto> = mutableListOf()
                client.getPokedexPage(limit, offset)
                    .onSuccess { pokemonPageDto ->
                        pokemonDtoList.addAll(pokemonPageDto.results)
                    }
                    .onError { error ->
                        return@coroutineScope ResultState.Error(error)
                    }

                val deferredPokemonList = pokemonDtoList.map { pokemonDto ->
                    async {
                        val pokemonDetailResult = client.getPokemonDetail(pokemonDto.url)
                        if (pokemonDetailResult is Result.Success) {
                            Pokemon(
                                name = pokemonDto.name,
                                type = pokemonDetailResult.data.types.asDomainModel(),
                                sprite = pokemonDetailResult.data.sprite.frontDefault,
                            )
                        } else {
                            null
                        }
                    }
                }
                ResultState.Success(deferredPokemonList.awaitAll().filterNotNull())
            }
        } catch (e: Exception) {
            return ResultState.Error(NetworkError.UNKNOWN)
        }
    }
}