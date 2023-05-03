package com.ivettevaldez.pokedex.pokemon.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ivettevaldez.pokedex.abilities.Ability
import com.ivettevaldez.pokedex.pokemon.PokemonUI
import com.ivettevaldez.pokedex.types.Type

@Dao
abstract class PokemonDao {

    // TODO: Extract nested entities' logic into standalone DAOs
    @Transaction
    @Query("")
    fun insertPokemonWithAbilitiesAndTypes(
        pokemon: Pokemon,
        abilities: List<Ability>,
        types: List<Type>
    ) {
        insertPokemon(pokemon)
        // Abilities
        abilities.forEach {
            insertAbility(it)
            val pokemonAbilityCrossRef = PokemonAbilityCrossRef(pokemon.name, it.name)
            insertPokemonWithAbility(pokemonAbilityCrossRef)
        }
        // Types
        types.forEach {
            insertType(it)
            val pokemonTypeCrossRef = PokemonTypeCrossRef(pokemon.name, it.name)
            insertPokemonWithType(pokemonTypeCrossRef)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPokemon(pokemon: Pokemon)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAllPokemons(pokemons: List<Pokemon>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertAbility(ability: Ability)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertPokemonWithAbility(pokemonAbilityCrossRef: PokemonAbilityCrossRef)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertType(type: Type)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insertPokemonWithType(pokemonTypeCrossRef: PokemonTypeCrossRef)

    @Transaction
    @Query("SELECT * FROM pokemons ORDER BY pokemonName")
    abstract fun getAll(): LiveData<List<PokemonUI>>

    @Transaction
    @Query("SELECT * FROM pokemons WHERE pokemonName LIKE '%' || :name || '%' ORDER BY pokemonName")
    abstract fun getFilteredByName(name: String): LiveData<List<PokemonUI>>

    @Transaction
    @Query("SELECT * FROM pokemons WHERE pokemonName = :name")
    abstract fun getByName(name: String): LiveData<PokemonUI>
}