package com.ivettevaldez.pokedex.species.networking.dto

import com.google.gson.annotations.SerializedName

data class FlavorTestEntryDto(
    @SerializedName("flavor_text") val description: String,
)