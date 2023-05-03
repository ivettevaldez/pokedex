package com.ivettevaldez.pokedex.pokemon.repository

import com.ivettevaldez.pokedex.global.resources.ResourceFetcher
import com.ivettevaldez.pokedex.pokemon.db.PokemonDao
import com.ivettevaldez.pokedex.pokemon.networking.PokemonRemoteDataSource

open class PokemonRepository(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonDao,
    private val mapper: PokemonMapper,
    private val resourceFetcher: ResourceFetcher
) {

    open suspend fun getAllPokemon() = resourceFetcher.fetchData(
        dbQuery = { localDataSource.getAll() },
        networkCall = { remoteDataSource.fetchList() },
        saveCallResult = { localDataSource.insertAllPokemons(it.results) }
    )

    open fun getFilteredByName(name: String) = resourceFetcher.fetchLocalData {
        localDataSource.getFilteredByName(name)
    }

    open suspend fun getDetails(name: String) = resourceFetcher.fetchData(
        dbQuery = { localDataSource.getByName(name) },
        networkCall = { remoteDataSource.fetchDetails(name) },
        saveCallResult = {
            if (name.isNotEmpty()) {
                localDataSource.insertPokemonWithAbilitiesAndTypes(
                    mapper.mapPokemon(it),
                    mapper.mapAbilities(it),
                    mapper.mapTypes(it)
                )
            }
        }
    )
}