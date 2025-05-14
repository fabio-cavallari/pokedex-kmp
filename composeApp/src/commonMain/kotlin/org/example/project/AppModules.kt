package org.example.project

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.PokeApiClient
import org.example.project.presentation.viewmodel.PokemonListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val appModule = module {
    factory<HttpClient> { params ->
        val engine = params.get<HttpClientEngine>()
        HttpClient(engine) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }
    factory<PokeApiClient> { PokeApiClient(get(parameters = { parametersOf(getHttpClientEngine()) })) }

    viewModel { PokemonListViewModel() }
}