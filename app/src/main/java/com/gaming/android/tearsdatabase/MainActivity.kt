package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStoreOwner
import com.gaming.android.tearsdatabase.api.Endpoints
import com.gaming.android.tearsdatabase.data.DataSource
import com.gaming.android.tearsdatabase.data.SearchData.Companion.queryItems
import com.gaming.android.tearsdatabase.data.SortData.Companion.onSortMenuItemSelected
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewBuilder.Companion.CreateDrawer
import com.gaming.android.tearsdatabase.viewmodels.*

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
const val SORT_ID_INC = 19
const val SORT_DEF_INC = 20
const val SORT_DEF_DEC = 21

const val MENU_TYPE_WEAPONS = 1
const val MENU_TYPE_BOWS = 2
const val MENU_TYPE_SHIELDS = 3
const val MENU_TYPE_MATERIALS = 4
const val MENU_TYPE_ROASTED_FOOD = 5
const val MENU_TYPE_MEALS = 6
const val MENU_TYPE_ARMOR = 7
class MainActivity : AppCompatActivity(), ViewModelStoreOwner {
    private lateinit var bind: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()
    private val weaponsViewModel: WeaponsViewModel by viewModels()
    private val materialViewModel: MaterialsViewModel by viewModels()
    private val bowViewModel: BowsViewModel by viewModels()
    private val shieldViewModel: ShieldsViewModel by viewModels()
    private val roastedFoodViewModel: RoastedFoodViewModel by viewModels()
    private val mealsViewModel: MealsViewModel by viewModels()
    private val armorViewModel: ArmorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()

        Log.d(TAG, "onCreate(Bundle?) called")

        if(weaponsViewModel.items.isNullOrEmpty()) {
            Endpoints.fetchWeapons(
                updateWeapons = { weapons -> setWeapons(weapons) },
                buildView = { buildRecyclerView() },
                onFailure = { setWeapons(DataSource.weaponBackup(this)) }
            )

            Endpoints.fetchMaterials(
                updateMaterials = { setMaterials(it) },
                buildView = { buildRecyclerView() },
                onFailure = { setMaterials(DataSource.materialsBackup(this)) }
            )

            Endpoints.fetchBows(
                updateBows = { setBows(it) },
                buildView = { buildRecyclerView() },
                onFailure = { setBows(DataSource.bowsBackup(this)) }
            )

            Endpoints.fetchShields(
                updateShields = { setShields(it) },
                buildView = { buildRecyclerView() },
                onFailure = { setShields(DataSource.shieldsBackup(this)) }
            )
            Endpoints.fetchRoastedFood(
                update = { setRoastedFood(it) },
                buildView = { buildRecyclerView() },
                onFailure = { setRoastedFood(DataSource.roastedBackup(this)) }
            )
            Endpoints.fetchMeals(
                update = { setMeals(it) },
                buildView = { buildRecyclerView() },
                onFailure = { setMeals(DataSource.recipeBackup(this)) }
            )

            Endpoints.fetchArmor(
                update = { setArmor(it) },
                buildView = { buildRecyclerView() },
                onFailure = { setArmor(DataSource.armorBackup(this)) }
            )
        }
    }

    override fun onStart() {
        super.onStart()
        buildRecyclerView()
        Log.d(TAG, "onStart() called")
        DataSource.weaponBackup(this)
    }

    fun setNav(nav: String) {
        viewModel.navItem = nav
    }

    fun setWeapons(weapons: List<Weapon>) {
        val newList = mutableListOf<Weapon>()
        weapons.map {
            newList.add(it.setDrawable(this))
        }
        weaponsViewModel.items = newList.toSet().toList()
        weaponsViewModel.searchList = weaponsViewModel.items
    }

    fun setMaterials(materials: List<Material>) {
        val newList = mutableListOf<Material>()
        materials.map {
            newList.add(it.setDrawable(this))
        }
        materialViewModel.items = newList.toSet().toList()
        materialViewModel.searchList = materialViewModel.items
    }

    fun setBows(bows: List<Bow>) {
        val newList = mutableListOf<Bow>()
        bows.map {
            newList.add(it.setDrawable(this))
        }
        bowViewModel.items = newList.toSet().toList()
        bowViewModel.searchList = bowViewModel.items
    }

    fun setShields(shields: List<Shield>) {
        val newList = mutableListOf<Shield>()
        shields.map {
            newList.add(it.setDrawable(this))
        }
        shieldViewModel.items = newList.toSet().toList()
        shieldViewModel.searchList = shieldViewModel.items
    }

    fun setRoastedFood(roastedFood: List<RoastedFood>) {
        val newList = mutableListOf<RoastedFood>()
        roastedFood.map {
            newList.add(it.setDrawable(this))
        }
        roastedFoodViewModel.items = newList.toSet().toList()
        roastedFoodViewModel.searchList = roastedFoodViewModel.items
    }

    fun setMeals(meals: List<Meal>) {
        val newList = mutableListOf<Meal>()
        meals.map {
            newList.add(it.setDrawable(this))
        }
        mealsViewModel.items = newList.toSet().toList()
        mealsViewModel.searchList = mealsViewModel.items
    }

    fun setArmor(armor: List<Armor>) {
        val newList = mutableListOf<Armor>()
        armor.map {
            newList.add(it.setDrawable(this))
        }
        armorViewModel.items = newList.toSet().toList()
        armorViewModel.searchList = armorViewModel.items
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

    fun updateRoastedFood(rstf: List<RoastedFood>) {
        roastedFoodViewModel.searchList = rstf
    }

    fun updateMeals(mls: List<Meal>) {
        mealsViewModel.searchList = mls
    }

    fun updateArmor(arm: List<Armor>) {
        armorViewModel.searchList = arm
    }

    private fun buildRecyclerView(){
        setContent {
            TearsTheme {
                CreateDrawer(
                    nav = viewModel.navItem,
                    weapons = weaponsViewModel.getCurrent(),
                    materials = materialViewModel.getCurrent(),
                    bows = bowViewModel.getCurrent(),
                    shields = shieldViewModel.getCurrent(),
                    roastedFoods = roastedFoodViewModel.getCurrent(),
                    meals = mealsViewModel.getCurrent(),
                    armor = armorViewModel.getCurrent(),
                    onSetNav = { setNav(it) },
                    onQueryWeapon = {
                        queryItems(it, weaponsViewModel, { updateWeapons(it) })
                    },
                    onWeaponMenuItemSelected = {
                        onSortMenuItemSelected(it, weaponsViewModel, { updateWeapons(it) })
                    },
                    onQueryMaterial = {
                        queryItems(it, materialViewModel, { updateMaterials(it) })
                    },
                    onMaterialMenuItemSelected = {
                        onSortMenuItemSelected(it, materialViewModel, { updateMaterials(it) })
                    },
                    onQueryBow = {
                        queryItems(it, bowViewModel, { updateBows(it) })
                    },
                    onBowMenuItemSelected = {
                        onSortMenuItemSelected(it, bowViewModel, { updateBows(it) })
                    },
                    onQueryShield = {
                        queryItems(it, shieldViewModel, { updateShields(it) })
                    },
                    onShieldMenuItemSelected = {
                        onSortMenuItemSelected(it, shieldViewModel, { updateShields(it) })
                    },
                    onQueryRoastedFood = {
                        queryItems(it, roastedFoodViewModel, { updateRoastedFood(it) })
                    },
                    onRoastedFoodMenuItemSelected = {
                        onSortMenuItemSelected(it, roastedFoodViewModel, { updateRoastedFood(it) })
                    },
                    onQueryMeal = {
                        queryItems(it, mealsViewModel, { updateMeals(it) })
                    },
                    onMealMenuItemSelected = {
                        onSortMenuItemSelected(it, mealsViewModel, { updateMeals(it) })
                    },
                    onQueryArmor = {
                        queryItems(it, armorViewModel, { updateArmor(it) })
                    },
                    onArmorMenuItemSelected = {
                        onSortMenuItemSelected(it, armorViewModel, { updateArmor(it) })
                    }
                )
            }
        }
    }
}