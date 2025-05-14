package org.example.project.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.project.domain.model.Pokemon

class PokemonListViewModel: ViewModel() {
    private val _pokemonList: MutableStateFlow<List<Pokemon>> = MutableStateFlow(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    init {
        viewModelScope.launch {
            delay(2000)
            _pokemonList.value = listOf(
                Pokemon("pokemon a"),
                Pokemon("pokemon b"),
                Pokemon("pokemon c"),
                Pokemon("pokemon d"),
                Pokemon("pokemon e"),
            )
        }

    }
}