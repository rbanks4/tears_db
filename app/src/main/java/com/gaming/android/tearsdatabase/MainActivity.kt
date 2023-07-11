package com.gaming.android.tearsdatabase

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelStoreOwner
import com.gaming.android.tearsdatabase.api.Endpoints
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewBuilder

private const val TAG = "MainActivity"
const val SORT_DAMAGE_INC = 1
const val SORT_DAMAGE_DEC = 2
const val SORT_DURABILITY_INC = 3
const val SORT_DURABILITY_DEC = 4
class MainActivity : AppCompatActivity(), ViewModelStoreOwner {
    private lateinit var bind: ActivityMainBinding
    private val viewBuilder: ViewBuilder = ViewBuilder()


    private val weaponsViewModel: WeaponsViewModel by viewModels()
    private val materialViewModel: MaterialsViewModel by viewModels()

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
                updateMaterials = { setMaterials(it) }
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
        weaponsViewModel.weapons = newList.toSet().toList()
        weaponsViewModel.searchList = weaponsViewModel.weapons
    }

    fun setMaterials(materials: List<Material>) {
        materialViewModel.materials = materials
    }

    private fun buildRecyclerView(){
        setContent {
            TearsTheme {
                viewBuilder.CreateDrawer(
                    weapons = weaponsViewModel.searchList?:weaponsViewModel.weapons,
                    onQuery = { querySearch(it) },
                    onMenuItemSelected = { onMenuItemSelected(it) }
                )
            }
        }
    }

    fun querySearch(query: String): List<Weapon>? {
        Log.d(TAG, "QueryTextSubmit: $query")
        val regex = if(query.isNullOrBlank()) "." else query
        weaponsViewModel.searchString = regex

        weaponsViewModel.weapons.let {list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list!!.filter {
                if (it.sub_type.isNotEmpty())
                    it.sub_type[0].lowercase().matches(".*$regex.*".toRegex())
                else false
            }
            val finalList = nameList + subList
            Log.d(
                TAG,
                "QueryTextSubmit: results were ${finalList.toSet().toList()}"
            )

            weaponsViewModel.searchList = finalList.toSet().toList()
        }

        return weaponsViewModel.searchList
    }

    private fun onMenuItemSelected(choice: Int): List<Weapon>? {
        var listUpdate: List<Weapon>? = null
        val list =
            if(!weaponsViewModel.searchList.isNullOrEmpty())
                weaponsViewModel.searchList
            else weaponsViewModel.weapons

        when (choice) {
            SORT_DAMAGE_DEC ->
                listUpdate = list?.sortedByDescending { it.shown_attack }
            SORT_DAMAGE_INC ->
                listUpdate = list?.sortedBy { it.shown_attack }
            SORT_DURABILITY_DEC ->
                listUpdate = list?.sortedByDescending { it.durability }
            SORT_DURABILITY_INC ->
                listUpdate = list?.sortedBy { it.durability }
        }
        return if (!listUpdate.isNullOrEmpty()) {
            weaponsViewModel.searchList = listUpdate
            weaponsViewModel.searchList
        } else {
            weaponsViewModel.weapons
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewMessageCard() {
        TearsTheme {
            Surface {
                viewBuilder.WeaponCard(
                    wpn= Weapon("Boat Oar", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3)
                        .setDrawable(R.drawable.boat_oar),
                    onClick = {}
                )
            }
        }
    }

    @Preview
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewTopBar() {
        TearsTheme {
            viewBuilder.TopBar(onQuerySearch = {}, onListEdit = {}, onOpenDrawer = {})
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        TearsTheme {
            viewBuilder.WeaponList(weapons = SampleData.weapons, openDrawer = {}, onWeaponClick = {}, onQuery = { SampleData.weapons }, onMenuItemSelected = { SampleData. weapons })
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun DetailsView() {
        TearsTheme {
            viewBuilder.WeaponDetails(weapon = SampleData.weapons[1])
        }
    }
}