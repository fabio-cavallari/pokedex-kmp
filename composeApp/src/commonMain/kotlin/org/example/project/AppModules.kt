package org.example.project

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.example.project.data.client.PokeApiClientImpl
import org.example.project.data.client.getHttpClient
import org.example.project.data.repository.PokemonListRepository
import org.example.project.data.repository.PokemonListRepositoryImpl
import org.example.project.presentation.viewmodel.PokemonListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module

val appModule = module {
    factory<HttpClient> { params ->
        val engine = params.get<HttpClientEngine>()
        getHttpClient(engine)
    }
    factory<PokeApiClientImpl> { PokeApiClientImpl(get(parameters = { parametersOf(getHttpClientEngine()) })) }
    factory<PokemonListRepository> { PokemonListRepositoryImpl(get()) }
    viewModel { PokemonListViewModel(get()) }
}