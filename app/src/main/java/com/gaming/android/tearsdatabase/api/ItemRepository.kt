package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.models.Weapon

interface ItemRepository {

    suspend fun fetchWeapons(): List<Weapon>
}