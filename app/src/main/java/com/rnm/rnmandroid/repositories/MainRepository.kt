package com.rnm.rnmandroid.repositories

import com.rnm.rnmandroid.features.characterdetails.model.Episode
import com.rnm.rnmandroid.features.characters.model.CharactersAndNextPage
import com.rnm.rnmandroid.services.mappers.toDomain
import com.rnm.rnmandroid.services.network.NetworkResponse
import com.rnm.rnmandroid.services.network.RnmService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(private val rnmService: RnmService) {

    suspend fun getCharacters(page: Int? = null): NetworkResponse<CharactersAndNextPage> =
        try {
            val response = rnmService.getCharacters(page)
            if (response.isSuccessful) {
                val characters = response.body()!!.toDomain()
                NetworkResponse.Success(data = characters)
            } else {
                NetworkResponse.Error(Exception(response.message()))
            }
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }


    suspend fun getEpisodes(episodeNumbers: String): NetworkResponse<List<Episode>> =
        try {
            val response = rnmService.getEpisodes(episodeNumbers)
            if (response.isSuccessful) {
                val episodesDetails = response.body()!!.map { it.toDomain() }
                NetworkResponse.Success(data = episodesDetails)
            } else {
                NetworkResponse.Error(Exception(response.message()))
            }
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }

    suspend fun getEpisode(episodeNumber: String): NetworkResponse<Episode> =
        try {
            val response = rnmService.getEpisode(episodeNumber)
            if (response.isSuccessful) {
                val episodesDetails = response.body()!!.toDomain()
                NetworkResponse.Success(data = episodesDetails)
            } else {
                NetworkResponse.Error(Exception(response.message()))
            }
        } catch (e: HttpException) {
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            NetworkResponse.Error(e)
        }
}