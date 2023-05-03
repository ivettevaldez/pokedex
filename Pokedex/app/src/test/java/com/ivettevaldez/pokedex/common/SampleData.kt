package com.ivettevaldez.pokedex.common

import com.ivettevaldez.pokedex.abilities.Ability
import com.ivettevaldez.pokedex.abilities.AbilityDto
import com.ivettevaldez.pokedex.global.networking.wrappers.ItemName
import com.ivettevaldez.pokedex.pokemon.PokemonUI
import com.ivettevaldez.pokedex.pokemon.db.Pokemon
import com.ivettevaldez.pokedex.pokemon.networking.dto.DreamWorldDto
import com.ivettevaldez.pokedex.pokemon.networking.dto.ImageUrlDto
import com.ivettevaldez.pokedex.pokemon.networking.dto.OtherDto
import com.ivettevaldez.pokedex.pokemon.networking.dto.PokemonDto
import com.ivettevaldez.pokedex.species.db.Species
import com.ivettevaldez.pokedex.species.networking.dto.FlavorTestEntryDto
import com.ivettevaldez.pokedex.species.networking.dto.SpeciesDto
import com.ivettevaldez.pokedex.types.Type
import com.ivettevaldez.pokedex.types.TypeDto

object SampleData {

    // region Species
    val species1 = Species(
        id = 25,
        name = "pikachu",
        description = "When several of\nthese POKéMON\ngather, their\nfelectricity could\nbuild and cause\nlightning storms.",
    )

    private val species2 = Species(
        id = 4,
        name = "charmander",
        description = "Obviously prefers hot places. When it rains, steamis said to spout from the tip of its tail.",
    )

    private val species3 = Species(
        id = 1,
        name = "bulbasaur",
        description = "A strange seed was planted on its back at birth.The plant sprouts and grows with this POKéMON.",
    )

    val speciesDto = SpeciesDto(
        id = species1.id,
        name = species1.name,
        flavorTextEntries = listOf(FlavorTestEntryDto(species1.description)),
    )
    // endregion

    // region Abilities
    val ability = Ability(
        id = 1,
        name = "stench",
    )

    val abilityDto = AbilityDto(
        id = ability.id,
        item = ItemName(ability.name),
    )
    // endregion

    // region Types
    val type = Type(
        id = 1,
        name = "normal",
    )

    val typeDto = TypeDto(
        id = type.id,
        item = ItemName(type.name),
    )
    // endregion

    // region Pokémons
    val pokemon1 = Pokemon(
        id = 25,
        name = "pikachu",
        height = 4,
        weight = 60,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg",
        species = species1,
    )

    val pokemon2 = Pokemon(
        id = 4,
        name = "charmander",
        height = 6,
        weight = 85,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/4.svg",
        species = species2,
    )

    val pokemon3 = Pokemon(
        id = 1,
        name = "bulbasaur",
        height = 7,
        weight = 69,
        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/1.svg",
        species = species3,
    )

    val pokemonUI = PokemonUI(
        pokemon = pokemon1,
        abilities = listOf(ability),
        types = listOf(type),
    )

    val pokemonDto = PokemonDto(
        id = pokemon1.id,
        name = pokemon1.name,
        height = pokemon1.height,
        weight = pokemon1.weight,
        sprites = OtherDto(
            DreamWorldDto(
                ImageUrlDto(
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/25.svg"
                )
            )
        ),
        species = species1,
        abilities = listOf(abilityDto),
        types = listOf(typeDto),
    )
    // endregion
}