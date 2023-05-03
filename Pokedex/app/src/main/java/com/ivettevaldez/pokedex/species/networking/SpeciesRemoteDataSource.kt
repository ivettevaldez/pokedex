package com.ivettevaldez.pokedex.species.networking

import com.ivettevaldez.pokedex.global.di.application.AppScope
import com.ivettevaldez.pokedex.global.networking.BaseDataSource
import com.ivettevaldez.pokedex.pokemon.networking.PokemonApi
import javax.inject.Inject

@AppScope
open class SpeciesRemoteDataSource @Inject constructor(
    private val pokemonApi: PokemonApi
) : BaseDataSource() {

    open suspend fun fetchDetails(name: String) = getResult { pokemonApi.speciesDetails(name) }
}