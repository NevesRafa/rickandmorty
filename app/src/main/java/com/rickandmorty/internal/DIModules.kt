package com.rickandmorty.internal

import com.rickandmorty.data.remote.RickAndMortyApi
import com.rickandmorty.domain.RickAndMortyRepository
import com.rickandmorty.presentation.home.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DIModules {

    private const val BASE_URL_API = "https://rickandmortyapi.com/api/"

    val appModule = module {

        // api
        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RickAndMortyApi::class.java)
        }

        // repository
        factory { RickAndMortyRepository(get()) }

        // viewmodels
        viewModel { CharacterListViewModel(get()) }
    }
}