package com.ivettevaldez.pokedex.species.networking

import com.ivettevaldez.pokedex.common.SampleData
import com.ivettevaldez.pokedex.global.resources.Resource
import com.ivettevaldez.pokedex.pokemon.networking.PokemonApi
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
class SpeciesRemoteDataSourceTest {

    private lateinit var sut: SpeciesRemoteDataSource

    private val mockPokemonApi = mock(PokemonApi::class.java)

    private val species = SampleData.species1
    private val speciesDto = SampleData.speciesDto

    @Before
    fun setUp() {
        sut = SpeciesRemoteDataSource(mockPokemonApi)
    }

    @Test
    fun fetchspeciesDetails_success_returnsStatusAndData() = runTest {
        // Arrange
        successFetchingDetails()
        // Act
        val result = sut.fetchDetails(species.name)
        // Assert
        verify(mockPokemonApi, times(1)).speciesDetails(species.name)
        assertEquals(Resource.Status.SUCCESS, result.status)
        assertNull(result.message)
        assertNotNull(result.data)
        assertEquals(speciesDto, result.data)
    }

    @Test
    fun fetchspeciesDetails_failure_returnsStatus() = runTest {
        // Arrange
        failureFetchingDetails()
        // Act
        val result = sut.fetchDetails(species.name)
        // Assert
        verify(mockPokemonApi, times(1)).speciesDetails(species.name)
        assertEquals(Resource.Status.FAILURE, result.status)
        assertNotNull(result.message)
        assertNull(result.data)
    }

    private suspend fun successFetchingDetails() {
        `when`(mockPokemonApi.speciesDetails(species.name)).doReturn(
            Response.success(HttpURLConnection.HTTP_OK, speciesDto)
        )
    }

    private suspend fun failureFetchingDetails() {
        `when`(mockPokemonApi.speciesDetails(species.name)).doReturn(
            Response.error(
                HttpURLConnection.HTTP_INTERNAL_ERROR,
                ResponseBody.create(MediaType.parse("plain/text"), "")
            )
        )
    }
}