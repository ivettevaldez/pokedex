package com.ivettevaldez.pokedex.species.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class SpeciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(species: Species)

    @Transaction
    @Query("SELECT * FROM species WHERE speciesName = :name")
    abstract fun getByName(name: String): LiveData<Species>
}