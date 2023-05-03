package com.ivettevaldez.pokedex.types

import com.google.gson.annotations.SerializedName
import com.ivettevaldez.pokedex.global.networking.wrappers.ItemName

data class TypeDto(
    @SerializedName("id") val id: Long,
    @SerializedName("type") val item: ItemName
) {
    val name: String get() = item.name
}