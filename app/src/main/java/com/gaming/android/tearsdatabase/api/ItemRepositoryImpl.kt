package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.models.Armor
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Effect
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Meal
import com.gaming.android.tearsdatabase.models.RoastedFood
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val api: ApiService
): ItemRepository {
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

    override suspend fun fetchRoasted(): List<RoastedFood> {
        return api.getRoastedFoods().roasted
    }

    override suspend fun fetchShields(): List<Shield> {
        return api.getShields().shields
    }

    override suspend fun fetchWeapons(): List<Weapon> {
        return api.getWeapons().weapons
    }
}