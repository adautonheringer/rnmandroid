package com.rnm.rnmandroid.features.characters.model

import com.google.gson.annotations.SerializedName

data class Character(
    val name: String,
    val image: String,
    val status : String,
    val species : String,
)
