package com.ivettevaldez.pokedex.species.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * A Pokémon Species forms the basis for at least one Pokémon.
 * Attributes of a Pokémon species are shared across all varieties of Pokémon within the species.
 * A good example is Wormadam; Wormadam is the species which can be found
 * in three different varieties, Wormadam-Trash, Wormadam-Sandy and Wormadam-Plant.
 */
@Entity(tableName = "species")
data class Species(
    @SerializedName("id")
    var id: Long,

    @PrimaryKey(autoGenerate = false)
    @SerializedName("name")
    @ColumnInfo(name = "speciesName")
    var name: String,

    @SerializedName("description")
    var description: String
)
