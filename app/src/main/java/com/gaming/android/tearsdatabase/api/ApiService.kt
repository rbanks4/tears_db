package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.api.response.ArmorResponse
import com.gaming.android.tearsdatabase.api.response.BowsResponse
import com.gaming.android.tearsdatabase.api.response.EffectResponse
import com.gaming.android.tearsdatabase.api.response.MaterialsResponse
import com.gaming.android.tearsdatabase.api.response.MealsResponse
import com.gaming.android.tearsdatabase.api.response.RoastedFoodResponse
import com.gaming.android.tearsdatabase.api.response.ShieldsResponse
import com.gaming.android.tearsdatabase.api.response.WeaponsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

const val API_KEY_TAG = "api-key: "
const val KEY = "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ"
const val FIND_ALL = "data/v1/action/find"
interface ApiService {

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getWeapons(@Body request: DataRequest): Call<WeaponsResponse>

    @GET("weapons")
    suspend fun getWeapons(): WeaponsResponse

    @GET("materials")
    suspend fun getMaterials(): MaterialsResponse

    @GET("bows")
    suspend fun getBows(): BowsResponse

    @GET("armor")
    suspend fun getArmor(): ArmorResponse

    @GET("effects")
    suspend fun getEffects(): EffectResponse

    @GET("meals")
    suspend fun getMeals(): MealsResponse

    @GET("roasted")
    suspend fun getRoastedFoods(): RoastedFoodResponse

    @GET("shields")
    suspend fun getShields(): ShieldsResponse
}