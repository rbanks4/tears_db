package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.models.Weapon
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class Endpoints {
    companion object {
        fun fetchWeapons(updateWeapons: (List<Weapon>) -> Unit, buildView: () -> Unit) {
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = "weapons2")
            val call = apiService.getWeapons(dataRequest)

            call.enqueue(object : Callback<WeaponsResponse> {
                override fun onResponse(
                    call: Call<WeaponsResponse>,
                    response: Response<WeaponsResponse>
                ) {
                    if (response.isSuccessful) {
                        println("response successful")
                        println(response.body())
                        if (response.body() != null) {
                            val weapons = response.body()?.documents
                            weapons?.let {
                                updateWeapons(weapons)
                                buildView()
                                //todo have a backup
                                println("response list size is ${weapons.size}")
                            }
                        }

                    } else {
                        println("response failed")
                        println(response.code())
                        println(response.errorBody())
                    }
                }

                override fun onFailure(call: Call<WeaponsResponse>, t: Throwable) {
                    println("failed call: " + t.message)
                }
            })
        }
    }
}