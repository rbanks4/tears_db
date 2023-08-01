package com.gaming.android.tearsdatabase.data

import android.util.Log
import com.gaming.android.tearsdatabase.models.*
import com.gaming.android.tearsdatabase.viewmodels.*

const val TAG = "SearchData"

class SearchData {
    //todo could use generics(?) to make this code simplified (i.e. fun <T> querySearch(..., list: (List<T>) -> Unit):List<T>
    companion object {
        fun queryWeaponSearch(query: String, viewModel: ItemViewModel<Weapon>, updateSearchList: (List<Weapon>) -> Unit): List<Weapon>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if (query.isNullOrBlank()) "." else query.lowercase()
            var currentList = listOf<Weapon>()
            viewModel.searchString = regex

            viewModel.items.let { list ->
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

        fun queryMaterialSearch(query: String, viewModel: ItemViewModel<Material>, updateSearchList: (List<Material>) -> Unit): List<Material>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if(query.isNullOrBlank()) "." else query.lowercase()
            var currentList = listOf<Material>()
            viewModel.searchString = regex

            viewModel.items.let { list ->
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
            val regex = if(query.isNullOrBlank()) "." else query.lowercase()
            var currentList = listOf<Bow>()
            bowViewModel.searchString = regex

            bowViewModel.items.let { list ->
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
            val regex = if(query.isNullOrBlank()) "." else query.lowercase()
            var currentList = listOf<Shield>()
            shieldViewModel.searchString = regex

            shieldViewModel.items.let { list ->
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

        fun queryRoastedFoodSearch(query: String, viewModel: RoastedFoodViewModel, updateSearchList: (List<RoastedFood>) -> Unit): List<RoastedFood>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if(query.isNullOrBlank()) "." else query.lowercase()
            var currentList = listOf<RoastedFood>()
            viewModel.searchString = regex

            viewModel.items.let { list ->
                val nameList = list!!.filter {
                    it.name.lowercase().matches(".*$regex.*".toRegex())
                }
                val subList = list!!.filter {
                    if (it.sub_type.isNotEmpty())
                        it.sub_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                    else false
                }
                val subList2 = list!!.filter {
                    if (it.effect_type.isNotEmpty())
                        it.effect_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                    else false
                }
                val finalList = nameList + subList + subList2

                currentList = finalList.toSet().toList()
                updateSearchList(finalList.toSet().toList())
            }

            return currentList
        }

        fun queryMealSearch(query: String, viewModel: MealsViewModel, updateSearchList: (List<Meal>) -> Unit): List<Meal>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if(query.isNullOrBlank()) "." else query.lowercase()
            var currentList = listOf<Meal>()
            viewModel.searchString = regex

            viewModel.items.let { list ->
                val nameList = list!!.filter {
                    it.name.lowercase().matches(".*$regex.*".toRegex())
                }
                val subList = list!!.filter {
                    if (it.recipe.isNotEmpty())
                        it.recipe.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                    else false
                }
                val finalList = nameList + subList

                currentList = finalList.toSet().toList()
                updateSearchList(finalList.toSet().toList())
            }

            return currentList
        }

        fun queryArmor(query: String, viewModel: ArmorViewModel, updateSearchList: (List<Armor>) -> Unit): List<Armor>? {
            Log.d(TAG, "QueryTextSubmit: $query")
            val regex = if(query.isNullOrBlank()) "." else query.lowercase()
            var currentList = listOf<Armor>()
            viewModel.searchString = regex

            viewModel.items.let { list ->
                val nameList = list!!.filter {
                    it.name.lowercase().matches(".*$regex.*".toRegex())
                }
                val subList = list!!.filter {
                    if (it.set_name.isNotEmpty())
                        it.set_name.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                    else false
                }
                val subList2 = list!!.filter {
                    if (it.set_bonus.isNotEmpty())
                        it.set_bonus.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                    else false
                }
                val subList3 = list!!.filter {
                    if (it.effect.isNotEmpty())
                        it.effect.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                    else false
                }
                val finalList = nameList + subList + subList2 + subList3

                currentList = finalList.toSet().toList()
                updateSearchList(finalList.toSet().toList())
            }

            return currentList
        }
    }
}