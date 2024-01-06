package com.gaming.android.tearsdatabase

import android.content.Context
import com.gaming.android.tearsdatabase.api.ApiService
import com.gaming.android.tearsdatabase.data.DataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
private const val BASE_URL = "https://us-east-2.aws.data.mongodb-api.com/app/data-bxxyf/endpoint/"
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //injected into ItemRepository.kt constructor
    @Provides
    @Singleton
    fun providesApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}