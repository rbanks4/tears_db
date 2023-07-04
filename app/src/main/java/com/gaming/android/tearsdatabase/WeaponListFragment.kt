package com.gaming.android.tearsdatabase

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.gaming.android.tearsdatabase.databinding.FragmentWeaponListBinding

class WeaponListFragment(var weapons: List<Weapon>, var controller: FragmentController): Fragment(R.layout.fragment_weapon_list) {
    private lateinit var bind: FragmentWeaponListBinding
    private lateinit var listUpdater: ListUpdater
    val TAG = "WeaponsListFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

                searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        Log.d(TAG, "QueryTextSubmit: $query")
                        val regex = if(query.isNullOrBlank()) "." else query
                        val nameList = weapons.filter {
                            it.name.lowercase().matches(".*$regex.*".toRegex())
                        }
                        val subList = weapons.filter {
                            if(it.sub_type.isNotEmpty())
                                it.sub_type[0].lowercase().matches(".*$regex.*".toRegex())
                            else false
                        }
                        val finalList = nameList + subList
                        Log.d(TAG, "QueryTextSubmit: results were ${finalList.toSet().toList()}")
                        listUpdater.update(finalList.toSet().toList())
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if(newText.isNullOrBlank()) {
                            listUpdater.update(weapons)
                        }
                        return true
                    }

                })
                searchView.isIconified = false
                searchItem.actionView = searchView
                searchItem.icon = bind.root.context.getDrawable(R.drawable.search_vector)

                menuClear.setOnMenuItemClickListener {
                    searchView.setQuery("", false)
                    true
                }
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId) {
                    R.id.sort_damage_high_to_low ->
                        listUpdater.update(listUpdater.getList()
                            .sortedByDescending { it.shown_attack })
                    R.id.sort_durability_low_to_high ->
                        listUpdater.update(listUpdater.getList()
                            .sortedBy { it.shown_attack })
                    R.id.sort_durability_high_to_low ->
                        listUpdater.update(listUpdater.getList()
                            .sortedByDescending { it.durability })
                    R.id.sort_damage_low_to_high ->
                        listUpdater.update(listUpdater.getList()
                            .sortedBy { it.durability })
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val itemAdapter = ItemAdapter(weapons, controller)
        bind = FragmentWeaponListBinding.inflate(inflater, container,false)
        bind.mainList.adapter = itemAdapter
        listUpdater = itemAdapter
        bind.mainList.layoutManager = GridLayoutManager(bind.root.context, 3)
        return bind.root
    }
}