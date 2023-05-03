package com.ivettevaldez.pokedex.species.repository

import com.ivettevaldez.pokedex.global.di.application.AppScope
import com.ivettevaldez.pokedex.species.db.Species
import com.ivettevaldez.pokedex.species.networking.dto.SpeciesDto
import javax.inject.Inject

@AppScope
open class SpeciesMapper @Inject constructor() {

    open fun map(dto: SpeciesDto) = Species(
        id = dto.id,
        name = dto.name,
        description = dto.flavorTextEntries.first().description
    )
}