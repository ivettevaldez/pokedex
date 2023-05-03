package com.ivettevaldez.pokedex.pokemon.networking.dto

import com.google.gson.annotations.SerializedName

data class OtherDto(
    @SerializedName("other") val other: DreamWorldDto,
)