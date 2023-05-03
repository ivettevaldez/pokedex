package com.ivettevaldez.pokedex.pokemon.repository

import com.ivettevaldez.pokedex.abilities.Ability
import com.ivettevaldez.pokedex.global.di.application.AppScope
import com.ivettevaldez.pokedex.pokemon.db.Pokemon
import com.ivettevaldez.pokedex.pokemon.networking.dto.PokemonDto
import com.ivettevaldez.pokedex.types.Type
import javax.inject.Inject

@AppScope
open class PokemonMapper @Inject constructor() {

    open fun mapPokemon(dto: PokemonDto) = Pokemon(
        id = dto.id,
        name = dto.name,
        height = dto.height,
        weight = dto.weight,
        species = dto.species,
        imageUrl = dto.sprites.other.dreamWorld.imageUrl
    )

    // TODO: Extract nested entities' logic into standalone mappers
    open fun mapAbilities(dto: PokemonDto) = dto.abilities.map {
        Ability(id = it.id, name = it.name)
    }

    open fun mapTypes(dto: PokemonDto) = dto.types.map {
        Type(id = it.id, name = it.name)
    }
}