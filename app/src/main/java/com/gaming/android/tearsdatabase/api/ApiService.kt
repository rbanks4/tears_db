package com.gaming.android.tearsdatabase.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("api-key: " + "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ")
    @POST("action/find")
    fun getWeapons(@Body request: DataRequest): Call<WeaponsResponse>

    @Headers("api-key: " + "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ")
    @POST("action/find")
    fun getMaterials(@Body request: DataRequest): Call<MaterialsResponse>

    @Headers("api-key: " + "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ")
    @POST("action/find")
    fun getBows(@Body request: DataRequest): Call<BowsResponse>
}