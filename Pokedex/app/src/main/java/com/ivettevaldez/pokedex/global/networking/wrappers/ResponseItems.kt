package com.ivettevaldez.pokedex.global.networking.wrappers

import com.google.gson.annotations.SerializedName

/**
 * Wrapper for lists of generic items.
 */
data class ResponseItems<T>(
    @SerializedName("results") val results: List<T>
)
