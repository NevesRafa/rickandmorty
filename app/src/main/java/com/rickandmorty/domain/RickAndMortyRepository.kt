package com.rickandmorty.domain

import com.rickandmorty.data.remote.CharacterApiResultResponse
import com.rickandmorty.data.remote.RickAndMortyApi

class RickAndMortyRepository(private val api: RickAndMortyApi) {

    suspend fun loadCharactersFromApi(): List<CharacterApiResultResponse> {
        return api.getCharacters().results

    }
}