package com.rickandmorty.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharacterApiResponse

    @GET("character")
    suspend fun getPage(@Query("page") page: Int): CharacterApiResponse
}
