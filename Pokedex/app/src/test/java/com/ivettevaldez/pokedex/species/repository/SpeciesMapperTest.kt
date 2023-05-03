package com.ivettevaldez.pokedex.species.repository

import com.ivettevaldez.pokedex.common.SampleData
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SpeciesMapperTest {

    private lateinit var sut: SpeciesMapper

    private val speciesDto = SampleData.speciesDto
    private val expectedSpecies = SampleData.species1

    @Before
    fun setUp() {
        sut = SpeciesMapper()
    }

    @Test
    fun testInjectionSamples() {
        // Assert
        assertEquals(expectedSpecies.id, speciesDto.id)
        assertEquals(expectedSpecies.name, speciesDto.name)
        assertEquals(expectedSpecies.description, speciesDto.flavorTextEntries.first().description)
    }

    @Test
    fun map() {
        // Act
        val result = sut.map(speciesDto)
        // Assert
        assertEquals(expectedSpecies, result)
    }
}