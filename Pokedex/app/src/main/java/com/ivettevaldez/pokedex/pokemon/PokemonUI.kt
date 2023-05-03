package com.ivettevaldez.pokedex.pokemon

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ivettevaldez.pokedex.abilities.Ability
import com.ivettevaldez.pokedex.pokemon.db.Pokemon
import com.ivettevaldez.pokedex.pokemon.db.PokemonAbilityCrossRef
import com.ivettevaldez.pokedex.pokemon.db.PokemonTypeCrossRef
import com.ivettevaldez.pokedex.types.Type

data class PokemonUI(
    @Embedded val pokemon: Pokemon,

    @Relation(
        parentColumn = "pokemonName",
        entityColumn = "abilityName",
        associateBy = Junction(PokemonAbilityCrossRef::class)
    )
    val abilities: List<Ability>,

    @Relation(
        parentColumn = "pokemonName",
        entityColumn = "typeName",
        associateBy = Junction(PokemonTypeCrossRef::class)
    )
    val types: List<Type>,
)