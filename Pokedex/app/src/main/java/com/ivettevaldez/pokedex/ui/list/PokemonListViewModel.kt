package com.ivettevaldez.pokedex.ui.list

import androidx.lifecycle.*
import com.ivettevaldez.pokedex.global.resources.Resource
import com.ivettevaldez.pokedex.global.resources.Resource.Status.SUCCESS
import com.ivettevaldez.pokedex.pokemon.PokemonUI
import com.ivettevaldez.pokedex.pokemon.repository.PokemonRepository
import com.zhuinden.livedatacombinetuplekt.combineTuple
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This ViewModel decouples business logic from PokemonListFragment.
 */
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    lateinit var pokemon: LiveData<Resource<List<PokemonUI>>>
    private lateinit var allPokemon: LiveData<Resource<List<PokemonUI>>>

    private val nameQuery: MutableLiveData<String> = MutableLiveData("")

    private var hasMadeFirstQuery = false

    init {
        viewModelScope.launch {
            allPokemon = pokemonRepository.getAllPokemon()

            pokemon = combineTuple(allPokemon, nameQuery).switchMap { (pokemon, query) ->
                liveData(ioDispatcher) {
                    if (isDataNotFetchedYet(pokemon)) return@liveData
                    emitSource(pokemonRepository.getFilteredByName(query!!))
                }
            }.distinctUntilChanged()
        }
    }

    fun setNameQuery(name: String) {
        nameQuery.postValue(name)
        if (!hasMadeFirstQuery) hasMadeFirstQuery = true
    }

    private fun isDataNotFetchedYet(pokemon: Resource<List<PokemonUI>>?) =
        pokemon == null || (pokemon.status == SUCCESS && pokemon.data!!.isEmpty() && !hasMadeFirstQuery)
}