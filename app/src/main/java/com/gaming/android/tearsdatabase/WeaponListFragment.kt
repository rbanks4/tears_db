package com.gaming.android.tearsdatabase

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.theme.TearsTheme
import com.gaming.android.tearsdatabase.ui.ViewBuilder

private const val TAG = "WeaponsListFragment"
const val SORT_DAMAGE_INC = 1
const val SORT_DAMAGE_DEC = 2
const val SORT_DURABILITY_INC = 3
const val SORT_DURABILITY_DEC = 4
class WeaponListFragment: Fragment() {
    private var weapons: List<Weapon>? = mutableListOf()
    private var controller: FragmentController? = null

    private val weaponsViewModel: WeaponsViewModel by viewModels()
    private val viewBuilder: ViewBuilder = ViewBuilder()

    fun init(weaponList: List<Weapon>) {
        weapons = weaponList
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = activity as MainActivity
    }

    override fun onResume() {
        super.onResume()
    }

    fun showDetails(wpn: Weapon) {
        val f = WeaponDetailsFragment()
        f.init(wpn)
        controller?.transition(f)
    }

    fun querySearch(query: String): List<Weapon>? {
        Log.d(TAG, "QueryTextSubmit: $query")
        val regex = if(query.isNullOrBlank()) "." else query
        weaponsViewModel.searchString = regex

        weapons.let {list ->
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
            else weapons

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
            weapons
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                TearsTheme {
                    viewBuilder.CreateDrawer(
                        weapons = weaponsViewModel.searchList?:weapons,
                        onWeaponClick = { showDetails(it) },
                        onQuery = { querySearch(it) },
                        onMenuItemSelected = { onMenuItemSelected(it) }
                    )
                }
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
}