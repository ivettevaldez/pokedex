package com.ivettevaldez.pokedex.ui.common.navigation

import androidx.navigation.NavController
import com.ivettevaldez.pokedex.ui.list.PokemonListFragmentDirections
import javax.inject.Inject

class ScreensNavigator @Inject constructor() {

    fun navigateBack(navController: NavController) {
        navController.navigateUp()
    }

    fun toPokemonDetailsScreen(navController: NavController, pokemonName: String) {
        val directions = PokemonListFragmentDirections.navigateToPokemonDetails(pokemonName)
        navController.navigate(directions)
    }
}