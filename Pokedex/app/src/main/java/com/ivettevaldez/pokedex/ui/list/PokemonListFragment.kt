package com.ivettevaldez.pokedex.ui.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ivettevaldez.pokedex.R
import com.ivettevaldez.pokedex.databinding.FragmentPokemonListBinding
import com.ivettevaldez.pokedex.global.BaseFragment
import com.ivettevaldez.pokedex.global.resources.Resource.Status.*
import com.ivettevaldez.pokedex.pokemon.PokemonUI
import com.ivettevaldez.pokedex.ui.common.messages.SnackBarHelper
import com.ivettevaldez.pokedex.ui.common.navigation.ScreensNavigator
import com.ivettevaldez.pokedex.ui.common.viewbinding.ViewBindingFactory
import com.ivettevaldez.pokedex.ui.common.viewmodels.ViewModelFactory
import javax.inject.Inject

private const val RIPPLE_EFFECT_DELAY = 250L

class PokemonListFragment : BaseFragment() {

    @Inject
    lateinit var viewBindingFactory: ViewBindingFactory
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var screensNavigator: ScreensNavigator
    @Inject
    lateinit var snackBarHelper: SnackBarHelper
    @Inject
    lateinit var pokemonRecyclerAdapter: PokemonRecyclerAdapter

    private var _binding: FragmentPokemonListBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory).get<PokemonListViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = viewBindingFactory.newPokemonListBinding(container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            setUpViews()
            setUpObservers()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpViews() {
        // Toolbar
        binding.toolbar.setTitle(getString(R.string.app_name))

        // List
        binding.recyclerPokemon.layoutManager = LinearLayoutManager(context)
        binding.recyclerPokemon.adapter = pokemonRecyclerAdapter
        pokemonRecyclerAdapter.onItemClickListener = { pokemon ->
            // Delay navigation in order to display a ripple effect on user click
            Handler(Looper.getMainLooper()).postDelayed({
                screensNavigator.toPokemonDetailsScreen(findNavController(), pokemon.name)
            }, RIPPLE_EFFECT_DELAY)
        }

        // EditText
        binding.editFilterPokemon.doOnTextChanged { input, _, _, _ ->
            viewModel.setNameQuery(input?.trim().toString())
        }
    }

    private fun setUpObservers() {
        viewModel.pokemon.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                SUCCESS -> {
                    bindData(resource.data)
                    showLoadingIndicator(false)
                }
                FAILURE -> {
                    snackBarHelper.showError(getString(R.string.failure_message), binding.root)
                    showLoadingIndicator(false)
                }
                LOADING -> {
                    showLoadingIndicator(true)
                }
            }
        }
    }

    // TODO: Show more Pokémon attributes
    private fun bindData(data: List<PokemonUI>?) {
        val pokemon = data?.map { it.pokemon } ?: listOf()
        pokemonRecyclerAdapter.bindData(pokemon)
    }

    private fun showLoadingIndicator(show: Boolean) {
        binding.progressList.visibility = if (show) View.VISIBLE else View.GONE
    }
}