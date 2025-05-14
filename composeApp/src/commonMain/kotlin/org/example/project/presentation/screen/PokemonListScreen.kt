package org.example.project.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.example.project.presentation.viewmodel.PokemonListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PokemonList() {
    val viewModel = koinViewModel<PokemonListViewModel>()
    val pokemonList by viewModel.pokemonList.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        content = {
            if (pokemonList.isEmpty()) {
                CircularProgressIndicator()
            } else {
                Text(pokemonList.joinToString(separator = "\n") { it.name })
            }
        }
    )
}