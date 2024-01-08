package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.api.response.ArmorResponse
import com.gaming.android.tearsdatabase.api.response.BowsResponse
import com.gaming.android.tearsdatabase.api.response.EffectResponse
import com.gaming.android.tearsdatabase.api.response.MaterialsResponse
import com.gaming.android.tearsdatabase.api.response.MealsResponse
import com.gaming.android.tearsdatabase.api.response.RoastedFoodResponse
import com.gaming.android.tearsdatabase.api.response.ShieldsResponse
import com.gaming.android.tearsdatabase.api.response.WeaponsResponse
import retrofit2.http.GET

const val API_KEY_TAG = "api-key: "
const val KEY = "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ"
const val FIND_ALL = "data/v1/action/find"
interface ApiService {

    @GET("weapons2")
    suspend fun getWeapons(): WeaponsResponse

    @GET("materials")
    suspend fun getMaterials(): MaterialsResponse

    @GET("bows")
    suspend fun getBows(): BowsResponse

    @GET("armor2")
    suspend fun getArmor(): ArmorResponse

    @GET("effects")
    suspend fun getEffects(): EffectResponse

    @GET("meals2")
    suspend fun getMeals(): MealsResponse

    @GET("roasted")
    suspend fun getRoastedFoods(): RoastedFoodResponse

    @GET("shields")
    suspend fun getShields(): ShieldsResponse
}