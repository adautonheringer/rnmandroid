package com.rnm.rnmandroid.features.characters.model

data class Character(
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val episodes: List<String>,
)
