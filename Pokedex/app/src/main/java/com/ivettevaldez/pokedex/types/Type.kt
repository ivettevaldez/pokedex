package com.ivettevaldez.pokedex.types

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Types are properties for Pokémon and their moves.
 */
@Entity(tableName = "types")
data class Type(
    var id: Long,
    @PrimaryKey(autoGenerate = false) @ColumnInfo("typeName") var name: String,
)
