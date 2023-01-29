package com.rnm.rnmandroid.services.network

import com.google.gson.annotations.SerializedName

data class CharactersDto(
    @SerializedName("info")
    val info: InfoDto?,
    @SerializedName("results")
    val characters: List<CharacterDto>?

) {
    data class InfoDto(
        @SerializedName("next")
        val nextPage : String?
    )

    data class CharacterDto(
        @SerializedName("name")
        val name : String?,
        @SerializedName("image")
        val image : String?,
        @SerializedName("status")
        val status : String?,
        @SerializedName("species")
        val species : String?,
    )
}
