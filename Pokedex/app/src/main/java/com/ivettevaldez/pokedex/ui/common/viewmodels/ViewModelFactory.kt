package com.ivettevaldez.pokedex.ui.common.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ivettevaldez.pokedex.ui.details.PokemonDetailsViewModel
import com.ivettevaldez.pokedex.ui.list.PokemonListViewModel
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val pokemonListViewModelProvider: Provider<PokemonListViewModel>,
    private val pokemonDetailsViewModelProvider: Provider<PokemonDetailsViewModel>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        PokemonListViewModel::class.java -> pokemonListViewModelProvider.get() as T
        PokemonDetailsViewModel::class.java -> pokemonDetailsViewModelProvider.get() as T
        else -> throw IllegalArgumentException("Unsupported ViewModel type: $modelClass")
    }
}