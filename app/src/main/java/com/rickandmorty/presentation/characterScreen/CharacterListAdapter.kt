package com.rickandmorty.presentation.characterScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rickandmorty.data.remote.CharacterApiResultResponse
import com.rickandmorty.databinding.ItemCharacterListBinding

class CharacterListAdapter(private val onCharacterClick: (CharacterApiResultResponse) -> Unit) : RecyclerView.Adapter<CharacterListViewHolder>() {

    private val dataset = mutableListOf<CharacterApiResultResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterListBinding.inflate(inflater, parent, false)
        return CharacterListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        holder.bind(dataset[position], onCharacterClick)
    }

    override fun getItemCount() = dataset.size

    fun addCharacterList(list: List<CharacterApiResultResponse>) {
        dataset.addAll(list)
        notifyDataSetChanged()
    }
}

class CharacterListViewHolder(private val binding: ItemCharacterListBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterApiResultResponse, onCharacterClick: (CharacterApiResultResponse) -> Unit) {
        binding.root.setOnClickListener {
            onCharacterClick(character)
        }

        binding.name.text = character.name
        binding.species.text = character.species
        binding.image.load(character.image)
    }
}
