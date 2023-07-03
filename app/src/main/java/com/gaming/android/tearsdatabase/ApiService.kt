package com.gaming.android.tearsdatabase

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("api-key: " + "xzEOCq63EkHsw8PIh9CAbDPuERVTOuNKJSQSivxYvZBcv8hmuwk6FfaZhuGXztoJ")
    @POST("action/find")
    fun getWeapons(@Body request: WeaponRequest): Call<Weapons>
}