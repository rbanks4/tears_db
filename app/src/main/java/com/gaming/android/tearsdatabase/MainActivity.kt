package com.gaming.android.tearsdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class MainActivity : AppCompatActivity() {
    private lateinit var bind: ActivityMainBinding
    private var CONNECT_STRING = "mongodb+srv://user:pw@myhobbydb.o8ovpd1.mongodb.net/?retryWrites=true&w=majority"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        fetchWeapons()
    }

    fun fetchWeapons() {
        val apiService = ApiClient.apiService
        val weaponRequest = WeaponRequest("myhobbydb", "totk", "weapons")
        val call = apiService.getWeapons(weaponRequest)

        call.enqueue(object : Callback<Weapons> {
            override fun onResponse(
                call: Call<Weapons>,
                response: Response<Weapons>
            ) {
                if(response.isSuccessful) {
                    println("response successful")
                    println(response.body())
                    if (response.body() != null) {
                        var weapons = response.body()?.documents
                        println("my favorite item from response is: ${weapons?.get(1)?.name}")
                        buildRecyclerView(weapons!!)
                    }

                } else {
                    println("response failed")
                    println(response.code())
                    println(response.errorBody())
                }
            }

            override fun onFailure(call: Call<Weapons>, t: Throwable) {
                println("failed call: " + t.message)
            }
        })
    }

    fun buildRecyclerView(weapons: List<Weapon>){
        bind.mainList.adapter = ItemAdapter(weapons)
        bind.mainList.layoutManager = GridLayoutManager(this, 3)
    }
}