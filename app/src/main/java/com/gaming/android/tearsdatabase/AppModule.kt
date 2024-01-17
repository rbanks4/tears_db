package com.gaming.android.tearsdatabase

import android.content.Context
import androidx.room.Room
import com.gaming.android.tearsdatabase.api.ApiService
import com.gaming.android.tearsdatabase.data.DataSource
import com.gaming.android.tearsdatabase.data.ItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
private const val BASE_URL = "https://us-east-2.aws.data.mongodb-api.com/app/data-bxxyf/endpoint/"
private const val DB_NAME = "item_database"
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

    @Provides
    @Singleton
    fun database(@ApplicationContext context: Context): ItemDatabase {
        return Room.databaseBuilder(
            context,
            ItemDatabase::class.java,
            DB_NAME
        ).build()
    }
}