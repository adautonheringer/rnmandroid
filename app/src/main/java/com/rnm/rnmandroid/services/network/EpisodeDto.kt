package com.rnm.rnmandroid.services.network

import com.google.gson.annotations.SerializedName

data class EpisodeDto(
    @SerializedName("name")
    val name : String?,
    @SerializedName("id")
    val id : String?,
    @SerializedName("episode")
    val number : String?,
)
