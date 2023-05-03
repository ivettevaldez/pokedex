package com.ivettevaldez.pokedex.ui.common.viewbinding

import android.view.LayoutInflater
import android.view.ViewGroup
import com.ivettevaldez.pokedex.databinding.ActivityMainBinding
import com.ivettevaldez.pokedex.databinding.FragmentPokemonDetailsBinding
import com.ivettevaldez.pokedex.databinding.FragmentPokemonListBinding
import com.ivettevaldez.pokedex.databinding.ItemPokemonBinding
import com.ivettevaldez.pokedex.global.di.activity.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class ViewBindingFactory @Inject constructor(
    private val layoutInflaterProvider: Provider<LayoutInflater>
) {

    fun newMainBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflaterProvider.get())
    }

    fun newPokemonListBinding(parent: ViewGroup?): FragmentPokemonListBinding {
        return FragmentPokemonListBinding.inflate(layoutInflaterProvider.get(), parent, false)
    }

    fun newPokemonItemBinding(parent: ViewGroup?): ItemPokemonBinding {
        return ItemPokemonBinding.inflate(layoutInflaterProvider.get(), parent, false)
    }

    fun newPokemonDetailsBinding(parent: ViewGroup?): FragmentPokemonDetailsBinding {
        return FragmentPokemonDetailsBinding.inflate(layoutInflaterProvider.get(), parent, false)
    }
}