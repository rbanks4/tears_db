package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelStoreOwner
import com.gaming.android.tearsdatabase.api.Endpoints
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import com.gaming.android.tearsdatabase.models.Weapon

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(), FragmentController, ViewModelStoreOwner {
    private lateinit var bind: ActivityMainBinding
    private lateinit var weaponsListFragment: WeaponListFragment
    private val weaponsViewModel: WeaponsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()

        Log.d(TAG, "onCreate(Bundle?) called")

        if(weaponsViewModel.weapons.isNullOrEmpty()) {
            Endpoints.fetchWeapons(
                updateWeapons = { weapons -> setWeapons(weapons) },
                buildView = { buildRecyclerView() }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        buildRecyclerView()
        Log.d(TAG, "onStart() called")
    }

    fun setWeapons(weapons: List<Weapon>) {
        val newList = mutableListOf<Weapon>()
        weapons.map {
            val wpn = it.setDrawable(this)
            newList.add(wpn)
        }
        weaponsViewModel.weapons = newList
    }

    private fun buildRecyclerView(){
        val weapons = weaponsViewModel.weapons
        weapons?.let {
            weaponsListFragment = WeaponListFragment()
            weaponsListFragment.init(it)
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