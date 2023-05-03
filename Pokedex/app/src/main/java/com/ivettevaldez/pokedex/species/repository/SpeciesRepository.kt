package com.ivettevaldez.pokedex.species.repository

import com.ivettevaldez.pokedex.global.resources.ResourceFetcher
import com.ivettevaldez.pokedex.species.db.SpeciesDao
import com.ivettevaldez.pokedex.species.networking.SpeciesRemoteDataSource

open class SpeciesRepository(
    private val remoteDataSource: SpeciesRemoteDataSource,
    private val localDataSource: SpeciesDao,
    private val mapper: SpeciesMapper,
    private val resourceFetcher: ResourceFetcher
) {

    open suspend fun getDetails(name: String) = resourceFetcher.fetchData(
        dbQuery = { localDataSource.getByName(name) },
        networkCall = { remoteDataSource.fetchDetails(name) },
        saveCallResult = {
            if (name.isNotEmpty()) localDataSource.insert(mapper.map(it))
        }
    )
}