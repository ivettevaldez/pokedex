package com.ivettevaldez.pokedex.species.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpeciesTypeConverter {

    @TypeConverter
    fun fromSpeciesToJson(species: Species?): String {
        return Gson().toJson(species)
    }

    @TypeConverter
    fun fromJsonToSpecies(jsonSpecies: String): Species? = try {
        val type = object : TypeToken<Species>() {}.type
        Gson().fromJson(jsonSpecies, type)
    } catch (ex: Exception) {
        null
    }
}