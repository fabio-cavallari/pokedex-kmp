package org.example.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.PokeApiClient
import org.example.project.data.util.onError
import org.example.project.data.util.onSuccess
import org.example.project.domain.model.Pokemon
import org.example.project.domain.model.asDomainModel

class PokemonListViewModel(
    private val client: PokeApiClient
): ViewModel() {
    private val _pokemonList: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            client.getPokedexPage(0, 0)
                .onSuccess { pokedexPageDto ->
                    val pokemonList = pokedexPageDto.results
                    _pokemonList.value = pokemonList.map { it.asDomainModel() }
                }
                .onError { networkError ->
                    _pokemonList.value = listOf(Pokemon("error"))
                }
        }
    }
}