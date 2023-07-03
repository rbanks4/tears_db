package com.gaming.android.tearsdatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call


class MainActivity : AppCompatActivity(), FragmentController {
    private lateinit var bind: ActivityMainBinding
    private var CONNECT_STRING = "mongodb+srv://user:pw@myhobbydb.o8ovpd1.mongodb.net/?retryWrites=true&w=majority"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        if(savedInstanceState == null) {
            fetchWeapons(this)
        }


    }

    fun fetchWeapons(activity: MainActivity) {
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
                        buildRecyclerView(weapons!!, activity)
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

    fun buildRecyclerView(weapons: List<Weapon>, activity: MainActivity){
        transition(WeaponListFragment(weapons, activity))
    }

    override fun transition(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_fragment_container, fragment)
            addToBackStack(null)
        }
    }
}