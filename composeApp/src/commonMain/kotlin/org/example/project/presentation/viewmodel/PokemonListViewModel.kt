package org.example.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.data.client.PokeApiClientImpl
import org.example.project.data.model.PokemonDto
import org.example.project.data.repository.PokemonListRepository
import org.example.project.data.util.Result
import org.example.project.data.util.ResultState
import org.example.project.data.util.onSuccess
import org.example.project.domain.model.Pokemon
import org.example.project.domain.model.asDomainModel

class PokemonListViewModel(
    private val repository: PokemonListRepository
): ViewModel() {
    private val _pokemonList: MutableStateFlow<ResultState<List<Pokemon>>> = MutableStateFlow(ResultState.Loading)
    val pokemonList = _pokemonList.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _pokemonList.value = repository.getPokemonList(10,0)
        }
    }
}