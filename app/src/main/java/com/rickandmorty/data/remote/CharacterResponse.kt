package com.rickandmorty.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterApiResponse(
    val results: List<CharacterApiResultResponse>
) : Parcelable

@Parcelize
data class CharacterApiResultResponse(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginResponse,
    val location: LocationResponse,
    val image: String
) : Parcelable

@Parcelize
data class OriginResponse(val name: String) : Parcelable

@Parcelize
data class LocationResponse(val name: String) : Parcelable
