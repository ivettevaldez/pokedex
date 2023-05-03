package com.ivettevaldez.pokedex.pokemon.networking

import androidx.annotation.VisibleForTesting
import com.ivettevaldez.pokedex.global.di.application.AppScope
import com.ivettevaldez.pokedex.global.networking.BaseDataSource
import javax.inject.Inject

@VisibleForTesting
const val DEFAULT_LIMIT = 150

@VisibleForTesting
const val DEFAULT_OFFSET = 0

@AppScope
open class PokemonRemoteDataSource @Inject constructor(
    private val pokemonApi: PokemonApi
) : BaseDataSource() {

    // TODO: Paginate results
    open suspend fun fetchList(limit: Int = DEFAULT_LIMIT, offset: Int = DEFAULT_OFFSET) =
        getResult { pokemonApi.pokemonsList(limit, offset) }

    open suspend fun fetchDetails(name: String) =
        getResult { pokemonApi.pokemonDetails(name) }
}