package com.rickandmorty.presentation.information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import com.rickandmorty.R
import com.rickandmorty.data.remote.CharacterApiResultResponse
import com.rickandmorty.databinding.ActivityInformationBinding

class InformationActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CHARACTER_INFORMATION = "extra.character.information"
    }

    lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.getParcelableExtra<CharacterApiResultResponse>(EXTRA_CHARACTER_INFORMATION)?.let {
            loadInformation(it)
        }
    }

    private fun loadInformation(character: CharacterApiResultResponse) {

        binding.characterImage.load(character.image)
        binding.name.text = character.name

        if (character.status == "Alive") {
            binding.status.text = character.status
        } else {
            binding.status.text = character.status
            binding.status.setTextColor(ContextCompat.getColor(this, R.color.red))
        }

        binding.species.text = character.species
        binding.type.text = character.type
        binding.gender.text = character.gender
        binding.origin.text = character.origin.name
        binding.location.text = character.location.name
    }


}