package com.rnm.rnmandroid.services.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RnmService {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int?
    ) : Response<CharactersDto>

    @GET("episode/{episodes}")
    suspend fun getEpisodes(
        @Path("episodes") episodeNumbers: String,
    ) : Response<List<EpisodeDto>>

    @GET("episode/{episodes}")
    suspend fun getEpisode(
        @Path("episodes") episodeNumber: String,
    ) : Response<EpisodeDto>

}