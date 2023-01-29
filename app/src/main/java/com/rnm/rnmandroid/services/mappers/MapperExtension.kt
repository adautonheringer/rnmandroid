package com.rnm.rnmandroid.services.mappers

import com.rnm.rnmandroid.features.characters.model.Character
import com.rnm.rnmandroid.features.characters.model.CharactersAndNextPage
import com.rnm.rnmandroid.services.network.CharactersDto

fun CharactersDto.toDomain(): CharactersAndNextPage =
    CharactersAndNextPage(
        characters = this.characters?.map {
            Character(
                name = it.name ?: "Missing Name",
                image = it.image ?: "",
                status = it.status ?: "",
                species = it.species ?: "",
            )
        } ?: listOf(),
        nextPage = this.info?.nextPage
    )