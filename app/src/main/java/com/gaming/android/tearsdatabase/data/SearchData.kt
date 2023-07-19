package com.gaming.android.tearsdatabase.data

import android.util.Log
import com.gaming.android.tearsdatabase.BowsViewModel
import com.gaming.android.tearsdatabase.MaterialsViewModel
import com.gaming.android.tearsdatabase.ShieldsViewModel
import com.gaming.android.tearsdatabase.WeaponsViewModel
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon

const val TAG = "SearchData"

class SearchData {
    companion object {
        fun queryWeaponSearch(query: String, weaponsViewModel: WeaponsViewModel, updateSearchList: (List<Weapon>) -> Unit): List<Weapon>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if (query.isNullOrBlank()) "." else query
            var currentList = listOf<Weapon>()
            weaponsViewModel.searchString = regex

            weaponsViewModel.weapons.let { list ->
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

                currentList = finalList.toSet().toList()
                updateSearchList(finalList.toSet().toList())
            }

            return currentList
        }

        fun queryMaterialSearch(query: String, materialViewModel: MaterialsViewModel, updateSearchList: (List<Material>) -> Unit): List<Material>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if(query.isNullOrBlank()) "." else query
            var currentList = listOf<Material>()
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

                currentList = finalList.toSet().toList()
                updateSearchList(finalList.toSet().toList())
            }

            return currentList
        }

        fun queryBowSearch(query: String, bowViewModel: BowsViewModel, updateSearchList: (List<Bow>) -> Unit): List<Bow>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if(query.isNullOrBlank()) "." else query
            var currentList = listOf<Bow>()
            bowViewModel.searchString = regex

            bowViewModel.bows.let {list ->
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
                val other = list!!.filter {
                    if (it.other.isNotEmpty())
                        it.other.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                    else false
                }
                val finalList = nameList + subList + subList2 + other

                currentList = finalList.toSet().toList()
                updateSearchList(finalList.toSet().toList())
            }

            return currentList
        }

        fun queryShieldSearch(query: String, shieldViewModel: ShieldsViewModel, updateSearchList: (List<Shield>) -> Unit): List<Shield>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if(query.isNullOrBlank()) "." else query
            var currentList = listOf<Shield>()
            shieldViewModel.searchString = regex

            shieldViewModel.shields.let {list ->
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

                currentList = finalList.toSet().toList()
                updateSearchList(finalList.toSet().toList())
            }

            return currentList
        }
    }
}