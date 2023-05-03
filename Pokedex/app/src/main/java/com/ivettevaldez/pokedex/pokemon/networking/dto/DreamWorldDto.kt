package com.ivettevaldez.pokedex.pokemon.networking.dto

import com.google.gson.annotations.SerializedName

data class DreamWorldDto(
    @SerializedName("dream_world") val dreamWorld: ImageUrlDto,
)