package com.rnm.rnmandroid.repositories

import android.util.Log
import com.rnm.rnmandroid.features.characters.model.CharactersAndNextPage
import com.rnm.rnmandroid.services.mappers.toDomain
import com.rnm.rnmandroid.services.network.CharactersDto
import com.rnm.rnmandroid.services.network.NetworkResponse
import com.rnm.rnmandroid.services.network.RnmService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepository @Inject constructor(private val rnmService: RnmService) {

    suspend fun getCharacters(page: Int? = null): NetworkResponse<CharactersAndNextPage> {
        Log.d("testando", "getting in Repo 1")

        return try {
            Log.d("testando", "getting in Repo 2")
            val response = rnmService.getCharacters(page)
            if (response.isSuccessful) {
                val characters = response.body()!!.toDomain()
                NetworkResponse.Success(data = characters)
            } else {
                NetworkResponse.Error(Exception(response.message()))
            }
        } catch (e: HttpException) {
            Log.d("testando", "getting in Repo 3")
            NetworkResponse.Error(e)
        } catch (e: IOException) {
            Log.d("testando", "getting in Repo 4")
            NetworkResponse.Error(e)
        }
    }
}