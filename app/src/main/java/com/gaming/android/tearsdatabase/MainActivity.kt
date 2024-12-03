package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

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

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ViewModelStoreOwner {

    private lateinit var bind: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        supportActionBar?.hide()

        Log.d(TAG, "onCreate(Bundle?) called")


    }

    override fun onStart() {
        super.onStart()
    }


}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route?:return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}