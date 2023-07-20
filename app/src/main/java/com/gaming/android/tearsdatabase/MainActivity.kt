package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStoreOwner
import com.gaming.android.tearsdatabase.api.Endpoints
import com.gaming.android.tearsdatabase.data.SearchData.Companion.queryBowSearch
import com.gaming.android.tearsdatabase.data.SearchData.Companion.queryMaterialSearch
import com.gaming.android.tearsdatabase.data.SearchData.Companion.queryShieldSearch
import com.gaming.android.tearsdatabase.data.SearchData.Companion.queryWeaponSearch
import com.gaming.android.tearsdatabase.data.SortData.Companion.onWeaponMenuItemSelected
import com.gaming.android.tearsdatabase.data.SortData.Companion.onMaterialMenuItemSelected
import com.gaming.android.tearsdatabase.data.SortData.Companion.onBowMenuItemSelected
import com.gaming.android.tearsdatabase.data.SortData.Companion.onShieldMenuItemSelected
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewBuilder.Companion.CreateDrawer

private const val TAG = "MainActivity"
const val SORT_DAMAGE_INC = 1
const val SORT_DAMAGE_DEC = 2
const val SORT_DURABILITY_INC = 3
const val SORT_DURABILITY_DEC = 4
const val SORT_HP_DEC = 5
const val SORT_SELLING_DEC = 6
const val SORT_SELLING_INC = 7
const val SORT_BUYING_DEC = 8
const val SORT_BUYING_INC = 9
const val SORT_SLIPPERINESS_DEC = 10
const val SORT_SLIPPERINESS_INC = 11
const val SORT_DRAWING_TIME_DEC = 12
const val SORT_DRAWING_TIME_INC = 14
const val SORT_RELOAD_TIME_DEC = 15
const val SORT_RELOAD_TIME_INC = 16
const val SORT_RANGE_DEC = 17
const val SORT_RANGE_INC = 18

const val MENU_TYPE_WEAPONS = 1
const val MENU_TYPE_BOWS = 2
const val MENU_TYPE_SHIELDS = 3
const val MENU_TYPE_MATERIALS = 4
class MainActivity : AppCompatActivity(), ViewModelStoreOwner {
    private lateinit var bind: ActivityMainBinding

    private val weaponsViewModel: WeaponsViewModel by viewModels()
    private val materialViewModel: MaterialsViewModel by viewModels()
    private val bowViewModel: BowsViewModel by viewModels()
    private val shieldViewModel: ShieldsViewModel by viewModels()

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

            Endpoints.fetchMaterials(
                updateMaterials = { setMaterials(it) },
                buildView = { buildRecyclerView() }
            )

            Endpoints.fetchBows(
                updateBows = { setBows(it) },
                buildView = { buildRecyclerView() }
            )

            Endpoints.fetchShields(
                updateShields = { setShields(it) },
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
            newList.add(it.setDrawable(this))
        }
        weaponsViewModel.weapons = newList.toSet().toList()
        weaponsViewModel.searchList = weaponsViewModel.weapons
    }

    fun setMaterials(materials: List<Material>) {
        val newList = mutableListOf<Material>()
        materials.map {
            newList.add(it.setDrawable(this))
        }
        materialViewModel.materials = newList.toSet().toList()
        materialViewModel.searchList = materialViewModel.materials
    }

    fun setBows(bows: List<Bow>) {
        val newList = mutableListOf<Bow>()
        bows.map {
            newList.add(it.setDrawable(this))
        }
        bowViewModel.bows = newList.toSet().toList()
        bowViewModel.searchList = bowViewModel.bows
    }

    fun setShields(shields: List<Shield>) {
        val newList = mutableListOf<Shield>()
        shields.map {
            newList.add(it.setDrawable(this))
        }
        shieldViewModel.shields = newList.toSet().toList()
        shieldViewModel.searchList = shieldViewModel.shields
    }

    fun updateWeapons(wpns: List<Weapon>) {
        weaponsViewModel.searchList = wpns
    }

    fun updateMaterials(mats: List<Material>) {
        materialViewModel.searchList = mats
    }

    fun updateBows(bows: List<Bow>) {
        bowViewModel.searchList = bows
    }

    fun updateShields(shds: List<Shield>) {
        shieldViewModel.searchList = shds
    }

    private fun buildRecyclerView(){
        setContent {
            TearsTheme {
                CreateDrawer(
                    weapons = weaponsViewModel.searchList?:weaponsViewModel.weapons,
                    materials = materialViewModel.searchList?:materialViewModel.materials,
                    bows = bowViewModel.searchList?:bowViewModel.bows,
                    shields = shieldViewModel.searchList?:shieldViewModel.shields,
                    onQueryWeapon = {
                        queryWeaponSearch(it, weaponsViewModel, { updateWeapons(it) })
                    },
                    onWeaponMenuItemSelected = {
                        onWeaponMenuItemSelected(it, weaponsViewModel, { updateWeapons(it) })
                    },
                    onQueryMaterial = {
                        queryMaterialSearch(it, materialViewModel, { updateMaterials(it) })
                    },
                    onMaterialMenuItemSelected = {
                        onMaterialMenuItemSelected(it, materialViewModel, { updateMaterials(it) })
                    },
                    onQueryBow = {
                        queryBowSearch(it, bowViewModel, { updateBows(it) })
                    },
                    onBowMenuItemSelected = {
                        onBowMenuItemSelected(it, bowViewModel, { updateBows(it) })
                    },
                    onQueryShield = {
                        queryShieldSearch(it, shieldViewModel, { updateShields(it) })
                    },
                    onShieldMenuItemSelected = {
                        onShieldMenuItemSelected(it, shieldViewModel, { updateShields(it) })
                    }
                )
            }
        }
    }
}