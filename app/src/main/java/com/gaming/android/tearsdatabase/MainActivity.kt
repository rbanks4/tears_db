package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class MainActivity : AppCompatActivity(), FragmentController, ViewModelStoreOwner {
    private lateinit var bind: ActivityMainBinding
    private lateinit var weaponsListFragment: WeaponListFragment
    private val weaponsViewModel: WeaponsViewModel by viewModels()
    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        Log.d(TAG, "onCreate(Bundle?) called")

        if(weaponsViewModel.getWeapons().isNullOrEmpty()) {
            fetchWeapons(this)
        }
    }

    override fun onStart() {
        super.onStart()
        buildRecyclerView(this)
        Log.d(TAG, "onStart() called")
    }

    private fun fetchWeapons(activity: MainActivity) {
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
                        val weapons = response.body()?.documents
                        if(!weapons.isNullOrEmpty()) {
                            println("my favorite item from response is: ${weapons[0].name}")
                            setWeapons(weapons)
                            buildRecyclerView(activity)
                        } else {
                            //todo have a backup
                            println("response returned empty list")
                        }
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

    fun setWeapons(weapons: List<Weapon>) {
        weaponsViewModel.setWeapons(weapons)
    }

    private fun buildRecyclerView(activity: MainActivity){
        val weapons = weaponsViewModel.getWeapons()
        if(!weapons.isNullOrEmpty()) {
            weaponsListFragment = WeaponListFragment()
            weaponsListFragment.init(weapons)
            transition(weaponsListFragment)
        }
    }

    override fun transition(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.main_fragment_container, fragment)
            addToBackStack(null)
        }
    }
}