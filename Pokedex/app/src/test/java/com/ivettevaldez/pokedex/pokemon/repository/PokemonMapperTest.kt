package com.ivettevaldez.pokedex.pokemon.repository

import com.ivettevaldez.pokedex.common.SampleData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PokemonMapperTest {

    private lateinit var sut: PokemonMapper

    private val pokemonDto = SampleData.pokemonDto
    private val expectedPokemon = SampleData.pokemon1

    private val abilityDto = SampleData.abilityDto
    private val expectedAbility = SampleData.ability

    private val typeDto = SampleData.typeDto
    private val expectedType = SampleData.type

    @Before
    fun setUp() {
        sut = PokemonMapper()
    }

    @Test
    fun testInjectionSamples() {
        // Pokemon
        assertEquals(expectedPokemon.id, pokemonDto.id)
        assertEquals(expectedPokemon.name, pokemonDto.name)
        assertEquals(expectedPokemon.height, pokemonDto.height)
        assertEquals(expectedPokemon.weight, pokemonDto.weight)
        assertEquals(expectedPokemon.imageUrl, pokemonDto.sprites.other.dreamWorld.imageUrl)
        assertEquals(expectedPokemon.species, pokemonDto.species)

        // Ability
        assertEquals(expectedAbility.id, abilityDto.id)
        assertEquals(expectedAbility.name, abilityDto.name)

        // Type
        assertEquals(expectedType.id, typeDto.id)
        assertEquals(expectedType.name, typeDto.name)
    }

    @Test
    fun mapPokemon() {
        // Act
        val result = sut.mapPokemon(pokemonDto)
        // Assert
        assertEquals(expectedPokemon, result)
    }

    @Test
    fun mapAbilities() {
        // Act
        val result = sut.mapAbilities(pokemonDto)
        // Assert
        assertEquals(listOf(expectedAbility), result)
    }

    @Test
    fun mapTypes() {
        // Act
        val result = sut.mapTypes(pokemonDto)
        // Assert
        assertEquals(listOf(expectedType), result)
    }
}