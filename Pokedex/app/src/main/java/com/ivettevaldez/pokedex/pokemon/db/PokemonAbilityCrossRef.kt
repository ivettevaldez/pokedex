package com.ivettevaldez.pokedex.pokemon.db

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["pokemonName", "abilityName"])
data class PokemonAbilityCrossRef(
    @ColumnInfo(index = true) val pokemonName: String,
    @ColumnInfo(index = true) val abilityName: String
)