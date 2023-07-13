package com.rickandmorty.domain

import com.rickandmorty.data.remote.CharacterApiResultResponse
import com.rickandmorty.data.remote.RickAndMortyApi

class RickAndMortyRepository(private val api: RickAndMortyApi) {

    suspend fun loadCharactersFromApi(): List<CharacterApiResultResponse> {
        try {
            return api.getCharacters().result
        } catch (e: Exception) {
            throw e
        }
    }

}