package org.example.project.data.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.project.data.model.PokedexPageDto
import org.example.project.data.model.PokemonDetailDto
import org.example.project.data.util.NetworkError
import org.example.project.data.util.Result

class PokeApiClientImpl(
    private val httpClient: HttpClient
) : PokeApiClient {
    override suspend fun getPokedexPage(limit: Int, offset: Int): Result<PokedexPageDto, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = "https://pokeapi.co/api/v2/pokemon/"
            ) {
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when (response.status.value) {
            in 200 .. 299 -> {
                val pokedexPage = response.body<PokedexPageDto>()
                Result.Success(pokedexPage)
            }
            else -> return Result.Error(NetworkError.UNKNOWN)
        }
    }

    override suspend fun getPokemonDetail(pokemonUrl: String): Result<PokemonDetailDto, NetworkError> {
        val response = try {
            httpClient.get(
                urlString = pokemonUrl
            ) {
                contentType(ContentType.Application.Json)
            }
        } catch (e: Exception) {
            return Result.Error(NetworkError.UNKNOWN)
        }
        return when (response.status.value) {
            in 200 .. 299 -> {
                val pokedexPage = response.body<PokemonDetailDto>()
                Result.Success(pokedexPage)
            }
            else -> return Result.Error(NetworkError.UNKNOWN)
        }
    }


}