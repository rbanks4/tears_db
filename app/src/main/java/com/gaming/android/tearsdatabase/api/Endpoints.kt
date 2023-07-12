package com.gaming.android.tearsdatabase.api

import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Weapon
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

const val WEAPONS = "weapons2"
const val MATERIALS = "materials"
class Endpoints {
    companion object {
        fun fetchWeapons(updateWeapons: (List<Weapon>) -> Unit, buildView: () -> Unit) {
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = WEAPONS)
            val call = apiService.getWeapons(dataRequest)

            call.enqueue(object : Callback<WeaponsResponse> {
                override fun onResponse(
                    call: Call<WeaponsResponse>,
                    response: Response<WeaponsResponse>
                ) {
                    if (response.isSuccessful) {
                        println("response successful")
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

        fun fetchMaterials(updateMaterials: (List<Material>) -> Unit, buildView: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = MATERIALS)
            val call = apiService.getMaterials(dataRequest)

            call.enqueue(object: Callback<MaterialsResponse> {
                override fun onResponse(
                    call: Call<MaterialsResponse>,
                    response: Response<MaterialsResponse>
                ) {
                    if (response.isSuccessful) {
                        println("response successful")
                        response.body()?.documents?.let { materials ->
                            updateMaterials(materials)
                            buildView()
                            println("materials response list size is ${materials.size}")
                        }

                    } else {
                        println("response failed")
                        println(response.code())
                        println(response.errorBody())
                    }
                }

                override fun onFailure(call: Call<MaterialsResponse>, t: Throwable) {
                    println("failed call: " + t.message)
                }

            })
        }
    }
}