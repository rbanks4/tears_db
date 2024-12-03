package com.gaming.android.tearsdatabase

import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.api.ItemRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun bindItemRepo(repo: ItemRepositoryImpl): ItemRepository
}