package com.rnm.rnmandroid.services.mappers

import com.rnm.rnmandroid.features.characterdetails.model.Episode
import com.rnm.rnmandroid.features.characters.model.Character
import com.rnm.rnmandroid.features.characters.model.CharactersAndNextPage
import com.rnm.rnmandroid.services.network.CharactersDto
import com.rnm.rnmandroid.services.network.EpisodeDto

fun CharactersDto.toDomain(): CharactersAndNextPage =
    CharactersAndNextPage(
        characters = this.characters?.map {
            Character(
                name = it.name ?: "Missing Name",
                image = it.image ?: "",
                status = it.status ?: "",
                species = it.species ?: "",
                episodes = it.episodes ?: listOf(),
            )
        } ?: listOf(),
        nextPage = this.info?.nextPage
    )

fun EpisodeDto.toDomain(): Episode =
    Episode(
        id = this.id ?: "",
        name = this.name ?: "",
        episodeNumber = this.number ?: ""
    )