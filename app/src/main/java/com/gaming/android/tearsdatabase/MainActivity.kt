package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelStoreOwner
import com.gaming.android.tearsdatabase.api.Endpoints
import com.gaming.android.tearsdatabase.data.DataSource
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import com.gaming.android.tearsdatabase.models.Item
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
    private val effectViewModel: EffectViewModel by viewModels()
    private val dataSource = DataSource(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()

        Log.d(TAG, "onCreate(Bundle?) called")

        Endpoints.fetchWeapons(
            update = { weapons -> weaponsViewModel.setup(weapons) { findDrawable(it)} },
            build = { buildRecyclerView() },
            onFailure = { weaponsViewModel.setup(dataSource.weaponBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchMaterials(
            update = { materialViewModel.setup(it) { findDrawable(it)} },
            onFailure = { materialViewModel.setup(dataSource.materialsBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchBows(
            update = { bowViewModel.setup(it) { findDrawable(it)} },
            onFailure = { bowViewModel.setup(dataSource.bowsBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchShields(
            update = { shieldViewModel.setup(it) { findDrawable(it)} },
            onFailure = { shieldViewModel.setup(dataSource.shieldsBackup()) { findDrawable(it)} }
        )
        Endpoints.fetchRoastedFood(
            update = { roastedFoodViewModel.setup(it) { findDrawable(it)} },
            onFailure = { roastedFoodViewModel.setup(dataSource.roastedBackup()) { findDrawable(it)} }
        )
        Endpoints.fetchMeals(
            update = { mealsViewModel.setup(it) { findDrawable(it)} },
            onFailure = { mealsViewModel.setup(dataSource.recipeBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchArmor(
            update = { armorViewModel.setup(it) { findDrawable(it)} },
            onFailure = { armorViewModel.setup(dataSource.armorBackup()) { findDrawable(it)} }
        )

        Endpoints.fetchEffects(
            update = { effectViewModel.setup(it) { findDrawable(it)} },
            onFailure = { effectViewModel.setup(dataSource.effectsBackup()) { findDrawable(it)} }
        )
    }

    fun <T> findDrawable(item: Item<T>): T {
        item.findDrawable(this)
        return item.get()
    }

    override fun onStart() {
        super.onStart()
        buildRecyclerView()
    }

    private fun buildRecyclerView(){
        setContent {
            TearsTheme {
                CreateDrawer(
                    nav = viewModel,
                    weapons = weaponsViewModel,
                    materials = materialViewModel,
                    bows = bowViewModel,
                    shields = shieldViewModel,
                    roastedFoods = roastedFoodViewModel,
                    meals = mealsViewModel,
                    armor = armorViewModel,
                    effects = effectViewModel
                )
            }
        }
    }
}