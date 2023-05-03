package com.ivettevaldez.pokedex.pokemon.db

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ivettevaldez.pokedex.common.SampleData
import com.ivettevaldez.pokedex.common.Utils.getDatabase
import com.ivettevaldez.pokedex.common.Utils.getOrAwaitValue
import com.ivettevaldez.pokedex.global.db.AppDatabase
import com.ivettevaldez.pokedex.pokemon.PokemonUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class PokemonDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: PokemonDao
    private lateinit var database: AppDatabase

    private val pokemonsList = listOf(SampleData.pokemon1, SampleData.pokemon2, SampleData.pokemon3)
    private val pokemon = SampleData.pokemon1
    private val ability = SampleData.ability
    private val type = SampleData.type

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = getDatabase(context)
        sut = database.pokemonDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertPokemon_getByName_returnsExpectedResult() {
        // Arrange
        val previousResult = sut.getByName(pokemon.name).getOrAwaitValue()
        // Act
        sut.insertPokemon(pokemon)
        val result = sut.getByName(pokemon.name).getOrAwaitValue()
        // Assert
        assertNull(previousResult)
        assertNotNull(result)
        assertEquals(pokemon, result.pokemon)
        assertEquals(0, result.abilities.size)
        assertEquals(0, result.types.size)
    }

    @Test
    fun insertAllPokemon_getAll_returnsExpectedResult() {
        // Arrange
        val previousResult = sut.getAll().getOrAwaitValue()
        // Act
        sut.insertAllPokemons(pokemonsList)
        val result = sut.getAll().getOrAwaitValue()
        // Assert
        assertTrue(previousResult.isEmpty())
        assertEquals(pokemonsList.size, result.size)
    }

    @Test
    fun insertPokemonWithAbilitiesAndTypes_getByName_returnsExpectedResult() = runTest {
        // Arrange
        val previousResult = sut.getByName(pokemon.name).getOrAwaitValue()
        // Act
        sut.insertPokemonWithAbilitiesAndTypes(pokemon, listOf(ability), listOf(type))
        val result = sut.getByName(pokemon.name).getOrAwaitValue()
        // Assert
        assertNull(previousResult)
        assertNotNull(result)
        assertEquals(pokemon, result.pokemon)
        assertEquals(1, result.abilities.size)
        assertEquals(ability, result.abilities.first())
        assertEquals(1, result.types.size)
        assertEquals(type, result.types.first())
    }

    @Test
    fun getFilteredByName_emptyInput_returnsFullList() {
        // Arrange
        val allPokemons: List<Pokemon> = pokemonsList
        sut.insertAllPokemons(allPokemons)
        val previousResult = sut.getAll().getOrAwaitValue()
        // Act
        val result: List<PokemonUI> = sut.getFilteredByName("").getOrAwaitValue()
        // Assert
        assertEquals(allPokemons.size, previousResult.size)
        assertEquals(allPokemons.size, result.size)
    }

    @Test
    fun getFilteredByName_validInput_returnsExpectedResult() {
        // Arrange
        val allPokemons: List<Pokemon> = pokemonsList
        sut.insertAllPokemons(allPokemons)
        val previousResult = sut.getAll().getOrAwaitValue()
        // Act
        val expectedResult: Pokemon = allPokemons.first()
        val nameFirstChars = expectedResult.name.subSequence(0, 3).toString()
        val result: List<PokemonUI> = sut.getFilteredByName(nameFirstChars).getOrAwaitValue()
        // Assert
        assertEquals(allPokemons.size, previousResult.size)
        assertEquals(1, result.size)
        assertEquals(expectedResult, result.first().pokemon)
    }

    @Test
    fun getFilteredByName_invalidInput_returnsEmptyList() {
        // Arrange
        val allPokemons: List<Pokemon> = pokemonsList
        sut.insertAllPokemons(allPokemons)
        val previousResult: List<PokemonUI> = sut.getAll().getOrAwaitValue()
        // Act
        val result: List<PokemonUI> = sut.getFilteredByName("x").getOrAwaitValue()
        // Assert
        assertEquals(allPokemons.size, previousResult.size)
        assertTrue(result.isEmpty())
    }
}