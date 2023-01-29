package com.rnm.rnmandroid.services.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val BASE_URL = "https://rickandmortyapi.com/api/"

    private val retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient()
                    .newBuilder()
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getRnmService(): RnmService =
        retrofit.create(RnmService::class.java)

}