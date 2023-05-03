package com.ivettevaldez.pokedex.pokemon.networking.dto

import com.google.gson.annotations.SerializedName

data class ImageUrlDto(
    @SerializedName("front_default") val imageUrl: String,
)