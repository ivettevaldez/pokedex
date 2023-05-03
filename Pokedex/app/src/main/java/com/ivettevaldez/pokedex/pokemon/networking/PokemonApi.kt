package com.ivettevaldez.pokedex.pokemon.networking

import com.ivettevaldez.pokedex.global.networking.wrappers.ResponseItems
import com.ivettevaldez.pokedex.pokemon.db.Pokemon
import com.ivettevaldez.pokedex.pokemon.networking.dto.PokemonDto
import com.ivettevaldez.pokedex.species.networking.dto.SpeciesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API interface to highly detailed objects built from data related to Pokémon.
 * It specifically covers the video game franchise.
 */
interface PokemonApi {

    @GET("/api/v2/pokemon/")
    suspend fun pokemonsList(
        @Query("limit") limit: Int?,
        @Query("offset") offset: Int?
    ): Response<ResponseItems<Pokemon>>

    @GET("/api/v2/pokemon/{name}")
    suspend fun pokemonDetails(@Path("name") name: String): Response<PokemonDto>

    @GET("/api/v2/pokemon-species/{name}")
    suspend fun speciesDetails(@Path("name") name: String): Response<SpeciesDto>
}