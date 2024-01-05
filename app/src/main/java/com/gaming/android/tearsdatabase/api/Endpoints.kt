package com.gaming.android.tearsdatabase.api

import android.util.Log
import com.gaming.android.tearsdatabase.models.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

const val TAG = "Endpoints"
const val WEAPONS = "weapons2"
const val MATERIALS = "materials"
const val BOWS = "bows"
const val SHIELDS = "shields"
const val ROASTED_FOOD = "roasted"
const val MEALS = "meals"
const val ARMOR = "armors"
const val EFFECTS = "effects"
class Endpoints {
    companion object {
        fun fetchMaterials(update: (List<Material>) -> Unit, onFailure: () -> Unit){
            val dataRequest = DataRequest(collection = MATERIALS)
            val call = ApiClient.apiService.getMaterials(dataRequest)
            val label = "$TAG.$MATERIALS"

            call.enqueue(object: Callback<MaterialsResponse> {
                override fun onResponse(
                    call: Call<MaterialsResponse>,
                    response: Response<MaterialsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.documents?.let { materials ->
                            update(materials)
                        }
                    } else {
                        notSuccessful(label, response)
                    }
                }

                override fun onFailure(call: Call<MaterialsResponse>, t: Throwable) {
                    onFailure()
                }

            })
        }

        fun fetchBows(update: (List<Bow>) -> Unit, onFailure: () -> Unit){
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
                        response.body()?.documents?.let { bows ->
                            update(bows)
                        }

                    } else {
                        notSuccessful(label, response)
                    }
                }

                override fun onFailure(call: Call<BowsResponse>, t: Throwable) {
                    onFailure()
                }

            })
        }

        fun fetchShields(update: (List<Shield>) -> Unit, onFailure: () -> Unit){
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
                        response.body()?.documents?.let { shields ->
                            update(shields)
                        }

                    } else {
                        notSuccessful(label, response)
                    }
                }

                override fun onFailure(call: Call<ShieldsResponse>, t: Throwable) {
                    onFailure()
                }

            })
        }

        fun fetchRoastedFood(update: (List<RoastedFood>) -> Unit, onFailure: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = ROASTED_FOOD)
            val call = apiService.getRoastedFood(dataRequest)
            val label = "$TAG.$ROASTED_FOOD"

            call.enqueue(object: Callback<RoastedFoodResponse> {
                override fun onResponse(
                    call: Call<RoastedFoodResponse>,
                    response: Response<RoastedFoodResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.documents?.let { roastedFood ->
                            update(roastedFood)
                        }

                    } else {
                        notSuccessful(label, response)
                    }
                }

                override fun onFailure(call: Call<RoastedFoodResponse>, t: Throwable) {
                    onFailure()
                }

            })
        }

        fun fetchMeals(update: (List<Meal>) -> Unit, onFailure: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = MEALS)
            val call = apiService.getMeals(dataRequest)
            val label = "$TAG.$MEALS"

            call.enqueue(object: Callback<MealsResponse> {
                override fun onResponse(
                    call: Call<MealsResponse>,
                    response: Response<MealsResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.documents?.let { meals ->
                            update(meals)
                        }

                    } else {
                        notSuccessful(label, response)
                    }
                }

                override fun onFailure(call: Call<MealsResponse>, t: Throwable) {
                    onFailure()
                }

            })
        }

        fun fetchArmor(update: (List<Armor>) -> Unit, onFailure: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = ARMOR)
            val call = apiService.getArmor(dataRequest)
            val label = "$TAG.$ARMOR"

            call.enqueue(object: Callback<ArmorResponse> {
                override fun onResponse(
                    call: Call<ArmorResponse>,
                    response: Response<ArmorResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.documents?.let { meals ->
                            update(meals)
                        }
                    } else {
                        notSuccessful(label, response)
                    }
                }

                override fun onFailure(call: Call<ArmorResponse>, t: Throwable) {
                    onFailure()
                }

            })
        }

        fun fetchEffects(update: (List<Effect>) -> Unit, onFailure: () -> Unit){
            val apiService = ApiClient.apiService
            val dataRequest = DataRequest(collection = EFFECTS)
            val call = apiService.getEffects(dataRequest)
            val label = "$TAG.$EFFECTS"

            call.enqueue(object: Callback<EffectResponse> {
                override fun onResponse(
                    call: Call<EffectResponse>,
                    response: Response<EffectResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.documents?.let {
                            update(it)
                        }

                    } else {
                        notSuccessful(label, response)
                    }
                }

                override fun onFailure(call: Call<EffectResponse>, t: Throwable) {
                    onFailure()
                }

            })
        }
        private fun notSuccessful(label: String, response: Response<*>) {
            Log.w(label,"response failed")
            Log.w(label,response.code().toString())
            Log.w(label,response.errorBody().toString())
        }
    }
}