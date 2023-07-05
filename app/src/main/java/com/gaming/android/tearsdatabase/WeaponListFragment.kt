package com.gaming.android.tearsdatabase

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.gaming.android.tearsdatabase.databinding.FragmentWeaponListBinding

    private const val TAG = "WeaponsListFragment"
class WeaponListFragment: Fragment(R.layout.fragment_weapon_list) {
    private lateinit var bind: FragmentWeaponListBinding
    private lateinit var listUpdater: ListUpdater
    private var weapons: List<Weapon>? = null
    private var controller: FragmentController? = null

    private val weaponsViewModel: WeaponsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = activity as MainActivity
    }

    fun init(weaponList: List<Weapon>) {
        weapons = weaponList
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object: MenuProvider {


            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.fragment_search_items, menu)

                val item = menu.add("Search")
                item.setIcon(android.R.drawable.ic_menu_search)
                item.setShowAsAction(
                    MenuItem.SHOW_AS_ACTION_IF_ROOM
                            or MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW
                )

                val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
                val menuClear: MenuItem = menu.findItem(R.id.menu_item_clear)
                val searchView = SearchView(bind.root.context)

                if(!weaponsViewModel.searchString.isNullOrBlank())
                    searchView.setQuery(weaponsViewModel.searchString, false)

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
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
                            listUpdater.update(finalList.toSet().toList())
                        } else {
                            Log.d(TAG, "weapons are null")

                        }

                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if(newText.isNullOrBlank() && !weapons.isNullOrEmpty()) {
                            listUpdater.update(weapons!!)
                            weaponsViewModel.searchList = weapons
                        }
                        return true
                    }

                })
                searchItem.actionView = searchView
                searchItem.icon = bind.root.context.getDrawable(R.drawable.search_vector)

                menuClear.setOnMenuItemClickListener {
                    searchView.setQuery("", false)
                    true
                }
                menuClear.actionView?.clearFocus()
                item.actionView?.clearFocus()

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    searchView.isIconified = false
                    searchView.isFocusedByDefault = false
                    searchView.clearFocus()
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                var listUpdate: List<Weapon>? = null
                when(menuItem.itemId) {
                    R.id.sort_damage_high_to_low ->
                        listUpdate = listUpdater.getList()
                            .sortedByDescending { it.shown_attack }
                    R.id.sort_durability_low_to_high ->
                        listUpdate = listUpdater.getList()
                            .sortedBy { it.shown_attack }
                    R.id.sort_durability_high_to_low ->
                        listUpdate = listUpdater.getList()
                            .sortedByDescending { it.durability }
                    R.id.sort_damage_low_to_high ->
                        listUpdate = listUpdater.getList()
                            .sortedBy { it.durability }
                }
                if(!listUpdate.isNullOrEmpty()) {
                    listUpdater.update(listUpdate)
                    weaponsViewModel.searchList = listUpdate
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentWeaponListBinding.inflate(inflater, container, false)
        if(!weapons.isNullOrEmpty()) {
            val weaponsList = if(weaponsViewModel.searchList != null) weaponsViewModel.searchList else weapons
            val itemAdapter = ItemAdapter(weaponsList!!, controller)
            listUpdater = itemAdapter
            bind.mainList.adapter = itemAdapter
            bind.mainList.layoutManager = GridLayoutManager(bind.root.context, 3)
        } else {
            Log.d(TAG, "onCreateView weapons are null")
        }
        return bind.root
    }
}