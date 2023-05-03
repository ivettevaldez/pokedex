package com.ivettevaldez.pokedex.species.networking.dto

import com.google.gson.annotations.SerializedName

data class SpeciesDto(
    @SerializedName("id") var id: Long,
    @SerializedName("name") var name: String,
    @SerializedName("flavor_text_entries") var flavorTextEntries: List<FlavorTestEntryDto>
)
