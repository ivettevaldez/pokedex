package com.ivettevaldez.pokedex.global.networking.wrappers

import com.google.gson.annotations.SerializedName

/**
 * Wrapper for items that have a "name" property.
 */
data class ItemName(
    @SerializedName("name") val name: String
)