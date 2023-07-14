package com.rickandmorty.presentation.characterScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickandmorty.domain.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel(private val repository: RickAndMortyRepository) : ViewModel() {

    val loadStateLiveData = MutableLiveData<CharacterListState>()
    val loadMoreStateLiveData = MutableLiveData<CharacterListState>()

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

    fun loadMore() {
        if (loadMoreStateLiveData.value is CharacterListState.Loading) {
            return
        }

        viewModelScope.launch {
            loadMoreStateLiveData.postValue(CharacterListState.Loading)
            delay(2000)
            try {
                val moreItems = withContext(Dispatchers.IO) {
                    repository.loadMore()
                }

                loadMoreStateLiveData.postValue(CharacterListState.Success(moreItems))
            } catch (error: Exception) {
                loadMoreStateLiveData.postValue(CharacterListState.Error(error.message))
            }
        }
    }
}