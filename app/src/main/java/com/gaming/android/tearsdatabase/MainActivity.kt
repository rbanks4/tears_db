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
import androidx.lifecycle.ViewModelStoreOwner
import com.gaming.android.tearsdatabase.api.Endpoints
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.databinding.ActivityMainBinding
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.MenuItem
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewBuilder

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

const val MENU_TYPE_WEAPONS = 1
const val MENU_TYPE_MATERIALS = 4
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
                updateMaterials = { setMaterials(it) },
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

    private fun buildRecyclerView(){
        setContent {
            TearsTheme {
                viewBuilder.CreateDrawer(
                    weapons = weaponsViewModel.searchList?:weaponsViewModel.weapons,
                    materials = materialViewModel.searchList?:materialViewModel.materials,
                    onQueryWeapon = { queryWeaponSearch(it) },
                    onWeaponMenuItemSelected = { onWeaponMenuItemSelected(it) },
                    onQueryMaterial = { queryMaterialSearch(it) },
                    onMaterialMenuItemSelected = { onMaterialMenuItemSelected(it) }
                )
            }
        }
    }

    fun queryWeaponSearch(query: String): List<Weapon>? {
        Log.d(TAG, "QueryTextSubmit: $query")
        val regex = if(query.isNullOrBlank()) "." else query
        weaponsViewModel.searchString = regex

        weaponsViewModel.weapons.let {list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list!!.filter {
                if (it.sub_type.isNotEmpty())
                    it.sub_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val subList2 = list!!.filter {
                if (it.sub_type2.isNotEmpty())
                    it.sub_type2.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val finalList = nameList + subList + subList2

            weaponsViewModel.searchList = finalList.toSet().toList()
        }

        return weaponsViewModel.searchList
    }

    fun queryMaterialSearch(query: String): List<Material>? {
        Log.d(TAG, "QueryTextSubmit: $query")
        val regex = if(query.isNullOrBlank()) "." else query
        materialViewModel.searchString = regex

        materialViewModel.materials.let {list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list!!.filter {
                if (it.sub_type.isNotEmpty())
                    it.sub_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val effects = list!!.filter {
                if (it.effect_type.isNotEmpty())
                    it.effect_type.lowercase().matches(".*$regex.*".toRegex())
                else false
            }
            val color = list!!.filter {
                if (it.dye_color.isNotEmpty())
                    it.dye_color.lowercase().matches(".*$regex.*".toRegex())
                else false
            }
            val finalList = nameList + subList + effects + color

            materialViewModel.searchList = finalList.toSet().toList()
        }

        return materialViewModel.searchList
    }

    private fun onWeaponMenuItemSelected(choice: Int): List<Weapon>? {
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

    private fun onMaterialMenuItemSelected(choice: Int): List<Material>? {
        var listUpdate: List<Material>? = null
        val list =
            if(!materialViewModel.searchList.isNullOrEmpty())
                materialViewModel.searchList
            else materialViewModel.materials

        when (choice) {
            SORT_HP_DEC ->
                listUpdate = list?.sortedByDescending { it.hp_recover }
                    ?.filter{ it.hp_recover != 0 && it.hp_recover != null }
            SORT_DAMAGE_INC ->
                listUpdate = list?.sortedBy { it.additional_damage }
                    ?.filter{ it.additional_damage != -1 }
            SORT_DAMAGE_DEC ->
                listUpdate = list?.sortedByDescending { it.additional_damage }
                    ?.filter{ it.additional_damage != -1 }
            SORT_SELLING_DEC ->
                listUpdate = list?.sortedByDescending { it.selling_price }
                    ?.filter{ it.selling_price != null }
            SORT_SELLING_INC ->
                listUpdate = list?.sortedBy { it.selling_price }
                    ?.filter{ it.selling_price != null }
            SORT_BUYING_DEC ->
                listUpdate = list?.sortedByDescending { it.buying_price }
                    ?.filter{ it.buying_price != null }
            SORT_BUYING_INC ->
                listUpdate = list?.sortedBy { it.buying_price }
                    ?.filter{ it.buying_price != null }

        }
        return if (!listUpdate.isNullOrEmpty()) {
            materialViewModel.searchList = listUpdate
            materialViewModel.searchList
        } else {
            materialViewModel.materials
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewWeaponCard() {
        TearsTheme {
            Surface {
                viewBuilder.WeaponCard(
                    wpn= SampleData.weapons[1],
                    onClick = {}
                )
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewMaterialCard() {
        TearsTheme {
            Surface {
                viewBuilder.MaterialCard(
                    mat= SampleData.materials[1],
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
            viewBuilder.TopBar(onQuerySearch = {}, onListEdit = {}, onOpenDrawer = {}, menuType = MENU_TYPE_WEAPONS)
        }
    }

    @Preview
    @Composable
    fun WeaponList() {
        TearsTheme {
            viewBuilder.WeaponList(weapons = SampleData.weapons, openDrawer = {}, onWeaponClick = {}, onQuery = { SampleData.weapons }, onWeaponMenuItemSelected = { SampleData. weapons })
        }
    }

    @Preview
    @Composable
    fun MaterialList() {
        TearsTheme {
            viewBuilder.MaterialList(materials = SampleData.materials, openDrawer = {}, onMaterialClick = {}, onQuery = { SampleData.materials }, onMenuItemSelected = { SampleData. materials })
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