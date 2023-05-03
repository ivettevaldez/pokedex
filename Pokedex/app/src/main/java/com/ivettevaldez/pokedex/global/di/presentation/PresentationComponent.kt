package com.ivettevaldez.pokedex.global.di.presentation

import com.ivettevaldez.pokedex.ui.MainActivity
import com.ivettevaldez.pokedex.ui.details.PokemonDetailsFragment
import com.ivettevaldez.pokedex.ui.list.PokemonListFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent
interface PresentationComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: PokemonListFragment)
    fun inject(fragment: PokemonDetailsFragment)
}