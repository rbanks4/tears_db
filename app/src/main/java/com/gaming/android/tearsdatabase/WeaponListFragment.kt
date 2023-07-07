package com.gaming.android.tearsdatabase

import android.content.res.Configuration
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gaming.android.tearsdatabase.theme.TearsTheme

private const val TAG = "WeaponsListFragment"
class WeaponListFragment: Fragment(R.layout.fragment_weapon_list) {
    private lateinit var listUpdater: ListUpdater
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
            )

            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }
            val surfaceColor by animateColorAsState(
                if(isExpanded) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.surface
            )

            Column (modifier = Modifier.clickable { isExpanded = !isExpanded }
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
    fun Conversation(weapons: List<Weapon>?){
        val weap = remember { mutableStateListOf<Weapon>() }
        var textState by remember { mutableStateOf("") }
        if(weapons != null) {
            weap.clear()
            weapons.map {
                weap.add(it)
            }
            Row(modifier = Modifier.padding(all = 8.dp)) {
                Scaffold(
                    topBar = {
                        TextField(
                            value = textState,
                            enabled = true,
                            onValueChange = { query ->
                                textState = query
                                weap.clear()
                                querySearch(query)?.map {
                                    weap.add(it)
                                }
                            },
                            readOnly = false,
                            modifier = Modifier.width(3000.dp),
                            leadingIcon = { Image(painter = painterResource(id = R.drawable.search_vector),
                                contentDescription = "search",
                                modifier = Modifier
                                    .size(20.dp)) },
                            supportingText = { Text("search for weapons(s)") }
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
                WeaponCard(Weapon("Boat Oar", 3, 3, listOf("Blunt"))
                    .setDrawable(R.drawable.boat_oar))
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        TearsTheme {
            Conversation(SampleData.weapons)
        }
    }

    override fun onResume() {
        super.onResume()
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val menuHost: MenuHost = requireActivity()
//
//        menuHost.addMenuProvider(object: MenuProvider {
//
//
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                menuInflater.inflate(R.menu.fragment_search_items, menu)
//
//                val item = menu.add("Search")
//                item.setIcon(android.R.drawable.ic_menu_search)
//                item.setShowAsAction(
//                    MenuItem.SHOW_AS_ACTION_IF_ROOM
//                            or MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
//                )
//
//                val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
//                val menuClear: MenuItem = menu.findItem(R.id.menu_item_clear)
//                val searchView = SearchView(bind.root.context)
//
//                if(!weaponsViewModel.searchString.isNullOrBlank())
//                    searchView.setQuery(weaponsViewModel.searchString, false)
//
//                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//                    override fun onQueryTextSubmit(query: String?): Boolean {
//                        querySearch(query)
//                        return true
//                    }
//
//                    override fun onQueryTextChange(newText: String?): Boolean {
//                        onQueryTextChange(newText)
//                        return true
//                    }
//
//                })
//                searchItem.actionView = searchView
//                searchItem.icon = bind.root.context.getDrawable(R.drawable.search_vector)
//
//                menuClear.setOnMenuItemClickListener {
//                    searchView.setQuery("", false)
//                    true
//                }
//                menuClear.actionView?.clearFocus()
//                item.actionView?.clearFocus()
//
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    searchView.isIconified = false
//                    searchView.isFocusedByDefault = false
//                    searchView.clearFocus()
//                }
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                var listUpdate: List<Weapon>? = null
//                when(menuItem.itemId) {
//                    R.id.sort_damage_high_to_low ->
//                        listUpdate = listUpdater.getList()
//                            .sortedByDescending { it.shown_attack }
//                    R.id.sort_durability_low_to_high ->
//                        listUpdate = listUpdater.getList()
//                            .sortedBy { it.shown_attack }
//                    R.id.sort_durability_high_to_low ->
//                        listUpdate = listUpdater.getList()
//                            .sortedByDescending { it.durability }
//                    R.id.sort_damage_low_to_high ->
//                        listUpdate = listUpdater.getList()
//                            .sortedBy { it.durability }
//                }
//                if(!listUpdate.isNullOrEmpty()) {
//                    listUpdater.update(listUpdate)
//                    weaponsViewModel.searchList = listUpdate
//                }
//                return true
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
//    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        bind = FragmentWeaponListBinding.inflate(inflater, container, false)
//        if(!weapons.isNullOrEmpty()) {
//            val weaponsList = if(weaponsViewModel.searchList != null) weaponsViewModel.searchList else weapons
//            val itemAdapter = ItemAdapter(weaponsList!!, controller)
//            listUpdater = itemAdapter
//            bind.mainList.adapter = itemAdapter
//            bind.mainList.layoutManager = GridLayoutManager(bind.root.context, 3)
//        } else {
//            Log.d(TAG, "onCreateView weapons are null")
//        }
//        return bind.root
//    }

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

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                TearsTheme {
                    val weaponsList = if(weaponsViewModel.searchList != null) weaponsViewModel.searchList else weapons
                    Conversation(weaponsList)
                }
            }
        }
    }
}