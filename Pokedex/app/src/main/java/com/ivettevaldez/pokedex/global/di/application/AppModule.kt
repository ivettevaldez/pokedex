package com.ivettevaldez.pokedex.global.di.application

import androidx.room.Room
import com.ivettevaldez.pokedex.global.PokedexApplication
import com.ivettevaldez.pokedex.global.db.AppDatabase
import com.ivettevaldez.pokedex.global.networking.UrlProvider
import com.ivettevaldez.pokedex.global.resources.ResourceFetcher
import com.ivettevaldez.pokedex.pokemon.db.PokemonDao
import com.ivettevaldez.pokedex.pokemon.networking.PokemonApi
import com.ivettevaldez.pokedex.pokemon.networking.PokemonRemoteDataSource
import com.ivettevaldez.pokedex.pokemon.repository.PokemonMapper
import com.ivettevaldez.pokedex.pokemon.repository.PokemonRepository
import com.ivettevaldez.pokedex.species.db.SpeciesDao
import com.ivettevaldez.pokedex.species.networking.SpeciesRemoteDataSource
import com.ivettevaldez.pokedex.species.repository.SpeciesMapper
import com.ivettevaldez.pokedex.species.repository.SpeciesRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
object AppModule {

    @Provides
    @AppScope
    fun ioDispatcher() = Dispatchers.IO

    @Provides
    @AppScope
    fun retrofit(urlProvider: UrlProvider): Retrofit = Retrofit.Builder()
        .baseUrl(urlProvider.getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @AppScope
    fun pokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)

    @Provides
    @AppScope
    fun appDatabase(application: PokedexApplication) = Room.databaseBuilder(
        application, AppDatabase::class.java, "app-database"
    ).build()

    @Provides
    @AppScope
    fun pokemonDao(appDatabase: AppDatabase) = appDatabase.pokemonDao()

    @Provides
    @AppScope
    fun speciesDao(appDatabase: AppDatabase) = appDatabase.speciesDao()

    @Provides
    @AppScope
    fun pokemonRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        pokemonDao: PokemonDao,
        pokemonMapper: PokemonMapper,
        resourceFetcher: ResourceFetcher,
    ) = PokemonRepository(pokemonRemoteDataSource, pokemonDao, pokemonMapper, resourceFetcher)

    @Provides
    @AppScope
    fun speciesRepository(
        speciesRemoteDataSource: SpeciesRemoteDataSource,
        speciesDao: SpeciesDao,
        speciesMapper: SpeciesMapper,
        resourceFetcher: ResourceFetcher,
    ) = SpeciesRepository(speciesRemoteDataSource, speciesDao, speciesMapper, resourceFetcher)
}