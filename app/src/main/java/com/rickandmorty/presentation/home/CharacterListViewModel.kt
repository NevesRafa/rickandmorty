package com.rickandmorty.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.domain.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(val repository: RickAndMortyRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<CharacterListState>()

    fun loadPokeList() {
        viewModelScope.launch {
            loadStateLiveData.postValue(CharacterListState.Loading)

            try {
                val characterList = withContext(Dispatchers.IO) {
                    repository.loadCharactersFromApi()
                }

                loadStateLiveData.postValue(CharacterListState.Success(characterList))
            } catch (error: Exception) {
                loadStateLiveData.postValue(CharacterListState.Error(error.message))
            }
        }
    }

}