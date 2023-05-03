package com.ivettevaldez.pokedex.species.db

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.ivettevaldez.pokedex.common.SampleData
import com.ivettevaldez.pokedex.common.Utils.getDatabase
import com.ivettevaldez.pokedex.common.Utils.getOrAwaitValue
import com.ivettevaldez.pokedex.global.db.AppDatabase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SpeciesDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: SpeciesDao
    private lateinit var database: AppDatabase

    private val species = SampleData.species1

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Application>()
        database = getDatabase(context)
        sut = database.speciesDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insert_getByName_returnsExpectedResult() {
        // Arrange
        val previousResult = sut.getByName(species.name).getOrAwaitValue()
        // Act
        sut.insert(species)
        val result = sut.getByName(species.name).getOrAwaitValue()
        // Assert
        assertNull(previousResult)
        assertNotNull(result)
        assertEquals(species, result)
    }
}