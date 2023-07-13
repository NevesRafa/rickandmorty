package com.rickandmorty.presentation.home

import com.rickandmorty.data.remote.CharacterApiResultResponse

sealed class CharacterListState {

    object Loading : CharacterListState()

    data class Success(val result: List<CharacterApiResultResponse>) : CharacterListState()

    data class Error(val errorMessage: String?) : CharacterListState()

}