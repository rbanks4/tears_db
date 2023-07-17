package com.gaming.android.tearsdatabase.api

import android.util.Log
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

const val TAG = "Endpoints"
const val WEAPONS = "weapons2"
const val MATERIALS = "materials"
const val BOWS = "bows"
const val SHIELDS = "shields"
class Endpoints {
    companion object {
        fun fetchWeapons(updateWeapons: (List<Weapon>) -> Unit, buildView: () -> Unit) {
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = WEAPONS)
            val call = apiService.getWeapons(dataRequest)
            val label = "$TAG.$WEAPONS"

            call.enqueue(object : Callback<WeaponsResponse> {
                override fun onResponse(
                    call: Call<WeaponsResponse>,
                    response: Response<WeaponsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.w(label,"response successful")
                        if (response.body() != null) {
                            val weapons = response.body()?.documents
                            weapons?.let {
                                updateWeapons(weapons)
                                buildView()
                                //todo have a backup
                                Log.w(label,"response list size is ${weapons.size}")
                            }
                        }

                    } else {
                        Log.w(label,"response failed")
                        Log.w(label,response.code().toString())
                        Log.w(label,response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<WeaponsResponse>, t: Throwable) {
                    Log.w(label,"failed call: " + t.message)
                }
            })
        }

        fun fetchMaterials(updateMaterials: (List<Material>) -> Unit, buildView: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = MATERIALS)
            val call = apiService.getMaterials(dataRequest)
            val label = "$TAG.$MATERIALS"

            call.enqueue(object: Callback<MaterialsResponse> {
                override fun onResponse(
                    call: Call<MaterialsResponse>,
                    response: Response<MaterialsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.w(label,"response successful")
                        response.body()?.documents?.let { materials ->
                            updateMaterials(materials)
                            buildView()
                            Log.w(label, "materials response list size is ${materials.size}")
                        }

                    } else {
                        Log.w(label, "response failed")
                        Log.w(label, response.code().toString())
                        Log.w(label, response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<MaterialsResponse>, t: Throwable) {
                    Log.w(label,"failed call: " + t.message)
                }

            })
        }

        fun fetchBows(updateBows: (List<Bow>) -> Unit, buildView: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = BOWS)
            val call = apiService.getBows(dataRequest)
            val label = "$TAG.$BOWS"

            call.enqueue(object: Callback<BowsResponse> {
                override fun onResponse(
                    call: Call<BowsResponse>,
                    response: Response<BowsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.w(label,"response successful")
                        response.body()?.documents?.let { bows ->
                            updateBows(bows)
                            buildView()
                            Log.w(label, "bows response list size is ${bows.size}")
                        }

                    } else {
                        Log.w(label, "response failed")
                        Log.w(label, response.code().toString())
                        Log.w(label, response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<BowsResponse>, t: Throwable) {
                    Log.w(label, "failed call: " + t.message)
                }

            })
        }

        fun fetchShields(updateShields: (List<Shield>) -> Unit, buildView: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = SHIELDS)
            val call = apiService.getShields(dataRequest)
            val label = "$TAG.$SHIELDS"

            call.enqueue(object: Callback<ShieldsResponse> {
                override fun onResponse(
                    call: Call<ShieldsResponse>,
                    response: Response<ShieldsResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.w(label, "response successful")
                        response.body()?.documents?.let { shields ->
                            updateShields(shields)
                            buildView()
                            Log.w(label, "shield response list size is ${shields.size}")
                        }

                    } else {
                        Log.w(label, "response failed")
                        Log.w(label, response.code().toString())
                        Log.w(label, response.errorBody().toString())
                    }
                }

                override fun onFailure(call: Call<ShieldsResponse>, t: Throwable) {
                    Log.w(label, "failed call: " + t.message)
                }

            })
        }
    }
}