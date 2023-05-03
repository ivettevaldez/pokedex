package com.ivettevaldez.pokedex.ui.list

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ivettevaldez.pokedex.databinding.ItemPokemonBinding
import com.ivettevaldez.pokedex.pokemon.db.Pokemon
import com.ivettevaldez.pokedex.ui.common.Utils.toTitleCase
import com.ivettevaldez.pokedex.ui.common.viewbinding.ViewBindingFactory
import java.util.*
import javax.inject.Inject

class PokemonRecyclerAdapter @Inject constructor(
    private val viewBindingFactory: ViewBindingFactory
) : RecyclerView.Adapter<PokemonRecyclerAdapter.PokemonViewHolder>() {

    private var pokemonList: List<Pokemon> = listOf()

    var onItemClickListener: ((Pokemon) -> Unit)? = null

    inner class PokemonViewHolder(val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(viewBindingFactory.newPokemonItemBinding(parent))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        // Set the view
        holder.binding.textPokemonName.text = pokemonList[position].name.toTitleCase()

        // TODO: Display the Pokémon ID

        // Set click event
        holder.binding.cardPokemonItem.setOnClickListener {
            onItemClickListener?.invoke(pokemonList[position])
        }
    }

    override fun getItemCount(): Int = pokemonList.size

    @SuppressLint("NotifyDataSetChanged")
    fun bindData(pokemonList: List<Pokemon>) {
        this.pokemonList = pokemonList
        notifyDataSetChanged()
    }
}