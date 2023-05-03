package com.ivettevaldez.pokedex.global.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ivettevaldez.pokedex.abilities.Ability
import com.ivettevaldez.pokedex.pokemon.db.Pokemon
import com.ivettevaldez.pokedex.pokemon.db.PokemonAbilityCrossRef
import com.ivettevaldez.pokedex.pokemon.db.PokemonDao
import com.ivettevaldez.pokedex.pokemon.db.PokemonTypeCrossRef
import com.ivettevaldez.pokedex.species.db.Species
import com.ivettevaldez.pokedex.species.db.SpeciesDao
import com.ivettevaldez.pokedex.species.db.SpeciesTypeConverter
import com.ivettevaldez.pokedex.types.Type

@Database(
    entities = [
        Pokemon::class,
        Species::class,
        Type::class,
        Ability::class,
        PokemonTypeCrossRef::class,
        PokemonAbilityCrossRef::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(SpeciesTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao
    abstract fun speciesDao(): SpeciesDao
}