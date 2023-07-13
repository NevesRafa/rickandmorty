package com.rickandmorty.data.model

data class CharacterDetails(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String?,
    val gender: String,
    val origin: String,
    val location: String,
    val image: String
)