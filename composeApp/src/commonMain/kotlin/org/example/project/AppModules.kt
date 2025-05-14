package org.example.project

import org.example.project.presentation.viewmodel.PokemonListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { PokemonListViewModel() }
}