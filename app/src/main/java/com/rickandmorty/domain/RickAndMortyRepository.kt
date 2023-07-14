package com.rickandmorty.domain

import com.rickandmorty.data.remote.CharacterApiResultResponse
import com.rickandmorty.data.remote.RickAndMortyApi

class RickAndMortyRepository(private val api: RickAndMortyApi) {

    private var idPageInitial = 2

    suspend fun loadCharactersFromApi(): List<CharacterApiResultResponse> {
        return api.getCharacters().results
    }

    suspend fun loadMore(): List<CharacterApiResultResponse> {
        val response = api.getPage(idPageInitial).results
        idPageInitial++
        return response
    }
}