package com.ivettevaldez.pokedex.abilities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Abilities provide passive effects for Pokémon in battle or in the overworld.
 * Pokémon have multiple possible abilities but can have only one ability at a time.
 */
@Entity(tableName = "abilities")
data class Ability(
    var id: Long,
    @PrimaryKey(autoGenerate = false) @ColumnInfo("abilityName") val name: String,
)