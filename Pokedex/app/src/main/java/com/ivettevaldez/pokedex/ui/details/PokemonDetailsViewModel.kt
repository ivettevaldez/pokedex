package com.ivettevaldez.pokedex.ui.details

import androidx.lifecycle.*
import com.ivettevaldez.pokedex.global.resources.Resource
import com.ivettevaldez.pokedex.pokemon.PokemonUI
import com.ivettevaldez.pokedex.pokemon.repository.PokemonRepository
import com.ivettevaldez.pokedex.species.db.Species
import com.ivettevaldez.pokedex.species.repository.SpeciesRepository
import com.zhuinden.livedatacombinetuplekt.combineTuple
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This ViewModel decouples business logic from PokemonDetailsFragment.
 */
class PokemonDetailsViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val speciesRepository: SpeciesRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    lateinit var pokemonDetails: LiveData<Resource<PokemonUI>>
    lateinit var species: LiveData<Resource<Species>>

    private lateinit var pokemon: LiveData<Resource<PokemonUI>>

    private val pokemonName: MutableLiveData<String> = MutableLiveData("")
    private val speciesName: MutableLiveData<String> = MutableLiveData("")

    private var gotSpeciesName = false

    init {
        viewModelScope.launch {
            initPokemon()
            initSpecies()
            initPokemonDetails()
        }
    }

    fun setPokemonName(name: String) {
        pokemonName.postValue(name)
    }

    private suspend fun initPokemon() {
        pokemon = pokemonName.switchMap {
            liveData(ioDispatcher) {
                emitSource(pokemonRepository.getDetails(it))
            }
        }
    }

    private suspend fun initSpecies() {
        species = speciesName.switchMap {
            liveData(ioDispatcher) {
                emitSource(speciesRepository.getDetails(it))
            }
        }
    }

    private suspend fun initPokemonDetails() {
        pokemonDetails = combineTuple(pokemon, species).switchMap { (pokemon, species) ->
            liveData(ioDispatcher) {
                pokemon ?: return@liveData

                val name = pokemon.data?.pokemon?.species?.name
                if (!gotSpeciesName && !name.isNullOrEmpty()) {
                    speciesName.postValue(name!!)
                    gotSpeciesName = true
                }

                emit(pokemon)
            }
        }.distinctUntilChanged()
    }
}