package com.gaming.android.tearsdatabase

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gaming.android.tearsdatabase.data.SampleData
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.theme.TearsTheme

private const val TAG = "WeaponsListFragment"
const val SORT_DAMAGE_INC = 1
const val SORT_DAMAGE_DEC = 2
const val SORT_DURABILITY_INC = 3
const val SORT_DURABILITY_DEC = 4
class WeaponListFragment: Fragment() {
    private var weapons: List<Weapon>? = mutableListOf()
    private var controller: FragmentController? = null

    private val weaponsViewModel: WeaponsViewModel by viewModels()

    fun init(weaponList: List<Weapon>) {
        weapons = weaponList
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = activity as MainActivity
    }

    @Composable
    fun WeaponCard(wpn: Weapon) {
        Column(modifier = Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = wpn.image),
                contentDescription = wpn.name,
                modifier = Modifier
                    .size(100.dp)
                    .clickable{
                        val f = WeaponDetailsFragment()
                        f.init(wpn)
                        controller?.transition(f)
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if(isExpanded) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            )

            Column (modifier = Modifier
                .clickable { isExpanded = !isExpanded }
                .align(Alignment.CenterHorizontally)){
                Text(
                    text = wpn.name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 1.dp,
                    color = surfaceColor,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(1.dp)
                ) {
                    Text(
                        text = "Damage: ${wpn.shown_attack} \nDurability: ${wpn.durability} \nSub Type: ${wpn.sub_type}",
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }

    @Composable
    fun TopBar(onQuerySearch: (String) -> Unit, onListEdit: (Int) -> Unit) {
        var textState by remember { mutableStateOf("") }
        Row(modifier = Modifier.padding(end = 8.dp)) {
            TextField(
                value = textState,
                enabled = true,
                onValueChange = { query ->
                    textState = query
                    onQuerySearch(query)
                },
                modifier = Modifier.width(1000.dp),
                readOnly = false,
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search_vector),
                        contentDescription = getString(R.string.search_description),
                        modifier = Modifier
                            .size(20.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary,
                            BlendMode.Color)
                    )},
                trailingIcon = {
                    MenuBox(
                        onListEdit = { onListEdit(it)
                        }
                    )
                },
                supportingText = { Text(getString(R.string.search_details)) }
            )
        }

    }

    @Composable
    fun MenuBox(onListEdit: (Int) -> Unit) {
        var expanded by remember { mutableStateOf(false) }
        IconButton(
            onClick = { expanded = !expanded },
            modifier = Modifier
                .size(20.dp)
        ) {
            Box {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = getString(R.string.menu_box_icon_description)
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.wrapContentSize(Alignment.TopEnd)
                ) {
                    DropdownMenuItem(
                        text = { Text(getString(R.string.sort_dmg_high_low)) },
                        onClick = { onListEdit(SORT_DAMAGE_DEC) }
                    )
                    DropdownMenuItem(
                        text = { Text(getString(R.string.sort_dmg_low_high)) },
                        onClick = {
                            onListEdit(SORT_DAMAGE_INC)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(getString(R.string.sort_dur_high_low)) },
                        onClick = {
                            onListEdit(SORT_DURABILITY_DEC)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(getString(R.string.sort_dur_low_high)) },
                        onClick = {
                            onListEdit(SORT_DURABILITY_INC)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun WeaponList(weapons: List<Weapon>?){
        val weap = remember { mutableStateListOf<Weapon>() }

        if(weapons != null) {
            weap.clear()
            weapons.map {
                weap.add(it)
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TopBar(
                            onQuerySearch = {
                                weap.clear()
                                querySearch(it)?.map {weapon ->
                                    weap.add(weapon)
                                }
                            },
                            onListEdit = {
                                weap.clear()
                                onMenuItemSelected(it)?.map{ weapon ->
                                    weap.add(weapon)
                                }
                            }
                        )
                    }
                ) { contentPadding ->
                    LazyVerticalGrid(columns = GridCells.Adaptive(100.dp),
                        modifier = Modifier.padding(contentPadding),
                        content = {
                            items(weap.size) { index ->
                                WeaponCard(weap[index])
                            }
                        })

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
                WeaponCard(
                    Weapon("Boat Oar", 3, 3, 3, 3, 3,"", 3, 3, "", 3, 3)
                    .setDrawable(R.drawable.boat_oar))
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        TearsTheme {
            WeaponList(SampleData.weapons)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun querySearch(query: String): List<Weapon>? {
        Log.d(TAG, "QueryTextSubmit: $query")
        val regex = if(query.isNullOrBlank()) "." else query
        weaponsViewModel.searchString = regex

        if(!weapons.isNullOrEmpty()) {
            val nameList = weapons!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = weapons!!.filter {
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
        } else {
            Log.d(TAG, "weapons are null")
        }
        return weaponsViewModel.searchList
    }

    fun onQueryTextChange(newText: String, weap: SnapshotStateList<Weapon>): List<Weapon>? {
        if(newText.isNullOrBlank() && !weapons.isNullOrEmpty()) {
            //listUpdater.update(weapons!!)
            weap.clear()
            weapons!!.map { weap.add(it) }
            weaponsViewModel.searchList = weapons
        }
        return weapons
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
                    val weaponsList = if(weaponsViewModel.searchList != null) weaponsViewModel.searchList else weapons
                    WeaponList(weaponsList)
                }
            }
        }
    }
}