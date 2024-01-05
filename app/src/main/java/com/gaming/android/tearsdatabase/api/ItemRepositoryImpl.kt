package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.models.Weapon
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val api: ApiService
): ItemRepository {
    override suspend fun fetchWeapons(): List<Weapon> {
        return api.getWeapons().weapons
    }
}