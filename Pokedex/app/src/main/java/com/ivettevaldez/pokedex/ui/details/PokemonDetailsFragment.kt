package com.ivettevaldez.pokedex.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.ivettevaldez.pokedex.R
import com.ivettevaldez.pokedex.databinding.FragmentPokemonDetailsBinding
import com.ivettevaldez.pokedex.global.BaseFragment
import com.ivettevaldez.pokedex.global.resources.Resource.Status.*
import com.ivettevaldez.pokedex.pokemon.PokemonUI
import com.ivettevaldez.pokedex.ui.common.Utils.cleanFormat
import com.ivettevaldez.pokedex.ui.common.Utils.dmToCentimeters
import com.ivettevaldez.pokedex.ui.common.Utils.dmToMeters
import com.ivettevaldez.pokedex.ui.common.Utils.hgToKilograms
import com.ivettevaldez.pokedex.ui.common.Utils.populate
import com.ivettevaldez.pokedex.ui.common.Utils.toTitleCase
import com.ivettevaldez.pokedex.ui.common.images.ImageLoader
import com.ivettevaldez.pokedex.ui.common.messages.SnackBarHelper
import com.ivettevaldez.pokedex.ui.common.navigation.ScreensNavigator
import com.ivettevaldez.pokedex.ui.common.viewbinding.ViewBindingFactory
import com.ivettevaldez.pokedex.ui.common.viewmodels.ViewModelFactory
import javax.inject.Inject

private const val ARG_POKEMON_NAME = "pokemon_name"

class PokemonDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewBindingFactory: ViewBindingFactory
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var screensNavigator: ScreensNavigator
    @Inject
    lateinit var snackBarHelper: SnackBarHelper
    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var pokemonName: String

    private var isPokemonLoaded = false
    private var isSpeciesLoaded = false
    private var isPictureLoaded = false

    private var _binding: FragmentPokemonDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory).get<PokemonDetailsViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
        pokemonName = requireArguments().getString(ARG_POKEMON_NAME)!!
        viewModel.setPokemonName(pokemonName)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = viewBindingFactory.newPokemonDetailsBinding(container)
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
        binding.toolbar.setTitle(pokemonName.toTitleCase())
        binding.toolbar.setNavigateUpListener {
            screensNavigator.navigateBack(findNavController())
        }

        showViews(false)
    }

    private fun setUpObservers() {
        viewModel.pokemonDetails.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                SUCCESS -> {
                    resource.data?.let { data ->
                        if (data.pokemon.id != 0L) {
                            bindData(data)
                            isPokemonLoaded = true
                            checkLoadingStatus()
                        }
                    }
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

        viewModel.species.observe(viewLifecycleOwner) { resource ->
            if (resource.status == SUCCESS) {
                resource.data?.description?.let {
                    binding.textSpeciesDesc.text = it.cleanFormat()
                    isSpeciesLoaded = true
                    checkLoadingStatus()
                }
            }
        }
    }

    private fun bindData(data: PokemonUI) {
        // Picture
        data.pokemon.imageUrl?.let { url ->
            imageLoader.loadImage(url, binding.imagePokemon) {
                isPictureLoaded = true
                checkLoadingStatus()
            }
        }

        // Text fields
        binding.textPokemonId.text = getString(R.string.pokemon_id_template, data.pokemon.id)
        binding.layoutPokemonDimens.textHeightValue.text = getString(
            R.string.height_template,
            data.pokemon.height.dmToCentimeters(),
            data.pokemon.height.dmToMeters()
        )
        binding.layoutPokemonDimens.textWeightValue.text = getString(
            R.string.weight_template, data.pokemon.weight.hgToKilograms()
        )

        // Chip Groups
        binding.chipGroupTypes.populate(data.types.map { it.name })
        binding.chipGroupAbilities.populate(data.abilities.map { it.name })
    }

    private fun showLoadingIndicator(show: Boolean) {
        binding.progressDetails.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showViews(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.INVISIBLE
        binding.textPokemonId.visibility = visibility
        binding.textSpeciesDesc.visibility = visibility
        binding.textAbilitiesTitle.visibility = visibility
        binding.imagePokemon.visibility = visibility
        binding.layoutPokemonDimens.root.visibility = visibility
        binding.chipGroupTypes.visibility = visibility
        binding.chipGroupAbilities.visibility = visibility
    }

    // TODO: Unify loading statuses in ViewModel
    private fun checkLoadingStatus() {
        if (isPokemonLoaded && isSpeciesLoaded && isPictureLoaded) {
            showLoadingIndicator(false)
            showViews(true)
        }
    }
}