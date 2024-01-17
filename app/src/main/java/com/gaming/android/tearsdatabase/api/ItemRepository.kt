package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.models.Armor
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Effect
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Meal
import com.gaming.android.tearsdatabase.models.RoastedFood
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun fetchArmor(): List<Armor>
    suspend fun fetchBows(): List<Bow>
    suspend fun fetchEffects(): List<Effect>
    suspend fun fetchMaterials(): List<Material>
    suspend fun fetchMeals(): List<Meal>
    suspend fun fetchRoasted(): List<RoastedFood>
    suspend fun fetchShields(): List<Shield>
    suspend fun fetchWeapons(): List<Weapon>
    suspend fun getMeal(name: String): Meal
    suspend fun saveMeals(meals: List<Meal>)
    fun searchMeal(name: String): Flow<List<Meal>>

}