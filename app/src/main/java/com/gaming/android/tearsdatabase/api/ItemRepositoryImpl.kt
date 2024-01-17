package com.gaming.android.tearsdatabase.api

import android.content.Context
import androidx.room.Room
import com.gaming.android.tearsdatabase.data.ItemDatabase
import com.gaming.android.tearsdatabase.models.Armor
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Effect
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Meal
import com.gaming.android.tearsdatabase.models.RoastedFood
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val api: ApiService
): ItemRepository {
    var database = Room.databaseBuilder(
    context,
    ItemDatabase::class.java,
    "item_database"
    ).build()
    override suspend fun fetchArmor(): List<Armor> {
        return api.getArmor().armor
    }

    override suspend fun fetchBows(): List<Bow> {
        return api.getBows().bows
    }

    override suspend fun fetchEffects(): List<Effect> {
        return api.getEffects().effects
    }

    override suspend fun fetchMaterials(): List<Material> {
        return api.getMaterials().materials
    }

    override suspend fun fetchMeals(): List<Meal> {
        return api.getMeals().meals
    }

    override suspend fun saveMeals(meals: List<Meal>) {
        database.mealDao().addAllMeals(meals)
    }

    override suspend fun fetchRoasted(): List<RoastedFood> {
        return api.getRoastedFoods().roasted
    }

    override suspend fun fetchShields(): List<Shield> {
        return api.getShields().shields
    }

    override suspend fun fetchWeapons(): List<Weapon> {
        return api.getWeapons().weapons
    }

    override suspend fun getMeal(name: String): Meal = database.mealDao().getMeal(name)

    override fun searchMeal(name: String): Flow<List<Meal>> = database.mealDao().search(name)
}