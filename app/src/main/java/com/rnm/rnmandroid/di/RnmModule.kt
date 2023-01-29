package com.rnm.rnmandroid.di

import com.rnm.rnmandroid.services.network.RetrofitInstance
import com.rnm.rnmandroid.services.network.RnmService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RnmModule {
    @Singleton
    @Provides
    fun provideImplementation(): RnmService = RetrofitInstance.getRnmService()
}