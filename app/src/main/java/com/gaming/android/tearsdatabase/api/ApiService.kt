package com.gaming.android.tearsdatabase.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

const val API_KEY_TAG = "api-key: "
const val KEY = "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ"
const val FIND_ALL = "action/find"
interface ApiService {

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getWeapons(@Body request: DataRequest): Call<WeaponsResponse>

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getMaterials(@Body request: DataRequest): Call<MaterialsResponse>

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getBows(@Body request: DataRequest): Call<BowsResponse>

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getShields(@Body request: DataRequest): Call<ShieldsResponse>

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getRoastedFood(@Body request: DataRequest): Call<RoastedFoodResponse>

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getMeals(@Body request: DataRequest): Call<MealsResponse>

    @Headers(API_KEY_TAG + KEY)
    @POST(FIND_ALL)
    fun getArmor(@Body request: DataRequest): Call<ArmorResponse>
}