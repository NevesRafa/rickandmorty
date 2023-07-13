package com.rickandmorty.data.remote

data class CharacterApiResponse(
    // val info: CharacterApiInfoResponse,
    val result: List<CharacterApiResultResponse>
)


//data class CharacterApiInfoResponse(
//    @SerializedName("next") val nextPager: Int,
//    @SerializedName("prev") val previousPager: Int,
//)

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
)

data class OriginResponse(val name: String)

data class LocationResponse(val name: String)
