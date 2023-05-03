package com.ivettevaldez.pokedex.pokemon.db

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.ivettevaldez.pokedex.species.db.Species

/**
 * Pokémon are the creatures that inhabit the world of the Pokémon games.
 */
@Entity(tableName = "pokemons")
data class Pokemon(
    @SerializedName("id")
    var id: Long,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("name")
    @ColumnInfo(name = "pokemonName")
    var name: String,

    @SerializedName("height")
    var height: Int,

    @SerializedName("weight")
    var weight: Int,

    @SerializedName("imageUrl")
    var imageUrl: String?,

    @SerializedName("species")
    var species: Species?,
)
