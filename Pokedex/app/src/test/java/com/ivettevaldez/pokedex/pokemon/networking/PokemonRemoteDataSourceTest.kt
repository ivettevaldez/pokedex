package com.ivettevaldez.pokedex.pokemon.networking

import com.ivettevaldez.pokedex.common.SampleData
import com.ivettevaldez.pokedex.global.networking.wrappers.ResponseItems
import com.ivettevaldez.pokedex.global.resources.Resource.*
import com.ivettevaldez.pokedex.global.resources.Resource.Status.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.kotlin.doReturn
import org.robolectric.RobolectricTestRunner
import retrofit2.Response
import java.net.HttpURLConnection

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class PokemonRemoteDataSourceTest {

    private lateinit var sut: PokemonRemoteDataSource

    private val mockPokemonApi = mock(PokemonApi::class.java)

    private val pokemon = SampleData.pokemon1
    private val pokemonDto = SampleData.pokemonDto
    private val pokemonsList = listOf(SampleData.pokemon1, SampleData.pokemon2, SampleData.pokemon3)

    @Before
    fun setUp() {
        sut = PokemonRemoteDataSource(mockPokemonApi)
    }

    @Test
    fun fetchPokemonsList_success_returnsStatusAndData() = runTest {
        // Arrange
        successFetchingList()
        // Act
        val result = sut.fetchList()
        // Assert
        verify(mockPokemonApi, times(1)).pokemonsList(DEFAULT_LIMIT, DEFAULT_OFFSET)
        assertEquals(SUCCESS, result.status)
        assertNull(result.message)
        assertNotNull(result.data)
        assertEquals(pokemonsList, result.data!!.results)
    }

    @Test
    fun fetchPokemonsList_failure_returnsStatus() = runTest {
        // Arrange
        failureFetchingList()
        // Act
        val result = sut.fetchList()
        // Assert
        verify(mockPokemonApi, times(1)).pokemonsList(DEFAULT_LIMIT, DEFAULT_OFFSET)
        assertEquals(FAILURE, result.status)
        assertNotNull(result.message)
        assertNull(result.data)
    }

    @Test
    fun fetchPokemonDetails_success_returnsStatusAndData() = runTest {
        // Arrange
        successFetchingDetails()
        // Act
        val result = sut.fetchDetails(pokemon.name)
        // Assert
        verify(mockPokemonApi, times(1)).pokemonDetails(pokemon.name)
        assertEquals(SUCCESS, result.status)
        assertNull(result.message)
        assertNotNull(result.data)
        assertEquals(pokemonDto, result.data)
    }

    @Test
    fun fetchPokemonDetails_failure_returnsStatus() = runTest {
        // Arrange
        failureFetchingDetails()
        // Act
        val result = sut.fetchDetails(pokemon.name)
        // Assert
        verify(mockPokemonApi, times(1)).pokemonDetails(pokemon.name)
        assertEquals(FAILURE, result.status)
        assertNotNull(result.message)
        assertNull(result.data)
    }

    private suspend fun successFetchingList() {
        `when`(mockPokemonApi.pokemonsList(DEFAULT_LIMIT, DEFAULT_OFFSET)).doReturn(
            Response.success(HttpURLConnection.HTTP_OK, ResponseItems(pokemonsList))
        )
    }

    private suspend fun failureFetchingList() {
        `when`(mockPokemonApi.pokemonsList(DEFAULT_LIMIT, DEFAULT_OFFSET)).doReturn(
            Response.error(
                HttpURLConnection.HTTP_INTERNAL_ERROR,
                ResponseBody.create(MediaType.parse("plain/text"), "")
            )
        )
    }

    private suspend fun successFetchingDetails() {
        `when`(mockPokemonApi.pokemonDetails(pokemon.name)).doReturn(
            Response.success(HttpURLConnection.HTTP_OK, pokemonDto)
        )
    }

    private suspend fun failureFetchingDetails() {
        `when`(mockPokemonApi.pokemonDetails(pokemon.name)).doReturn(
            Response.error(
                HttpURLConnection.HTTP_INTERNAL_ERROR,
                ResponseBody.create(MediaType.parse("plain/text"), "")
            )
        )
    }
}