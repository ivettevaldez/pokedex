package com.ivettevaldez.pokedex.abilities

import com.google.gson.annotations.SerializedName
import com.ivettevaldez.pokedex.global.networking.wrappers.ItemName

data class AbilityDto(
    @SerializedName("id") val id: Long,
    @SerializedName("ability") val item: ItemName
) {
    val name: String get() = item.name
}