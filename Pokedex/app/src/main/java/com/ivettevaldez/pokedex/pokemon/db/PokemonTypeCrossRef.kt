package com.ivettevaldez.pokedex.pokemon.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["pokemonName", "typeName"])
data class PokemonTypeCrossRef(
    @ColumnInfo(index = true) val pokemonName: String,
    @ColumnInfo(index = true) val typeName: String
)