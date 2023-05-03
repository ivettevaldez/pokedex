package com.ivettevaldez.pokedex.pokemon.networking.dto

import com.google.gson.annotations.SerializedName
import com.ivettevaldez.pokedex.abilities.AbilityDto
import com.ivettevaldez.pokedex.species.db.Species
import com.ivettevaldez.pokedex.types.TypeDto

data class PokemonDto(
    @SerializedName("id") var id: Long,
    @SerializedName("name") var name: String,
    @SerializedName("height") var height: Int,
    @SerializedName("weight") var weight: Int,
    @SerializedName("sprites") var sprites: OtherDto,
    @SerializedName("species") var species: Species?,
    @SerializedName("abilities") var abilities: List<AbilityDto>,
    @SerializedName("types") var types: List<TypeDto>,
)
