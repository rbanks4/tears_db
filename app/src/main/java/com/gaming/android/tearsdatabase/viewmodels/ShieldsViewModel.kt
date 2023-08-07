package com.gaming.android.tearsdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.SORT_DURABILITY_DEC
import com.gaming.android.tearsdatabase.SORT_DURABILITY_INC
import com.gaming.android.tearsdatabase.SORT_SLIPPERINESS_DEC
import com.gaming.android.tearsdatabase.SORT_SLIPPERINESS_INC
import com.gaming.android.tearsdatabase.models.Shield

private const val SHIELDS_ITEM = "shields"
class ShieldsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Shield> {
    override var items: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SHIELDS_ITEM)
        set(value) = savedStateHandle.set(SHIELDS_ITEM, value)

    override var searchList: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    override fun sort(choice: Int, list: List<Shield>?): List<Shield>? {
        return when (choice) {
            SORT_DURABILITY_DEC ->
                list?.sortedByDescending { it.durability }
            SORT_DURABILITY_INC ->
                list?.sortedBy { it.durability }
            SORT_SLIPPERINESS_DEC ->
                list?.sortedBy { it.shield_surfing_friction }
            SORT_SLIPPERINESS_INC ->
                list?.sortedByDescending { it.shield_surfing_friction }
            else -> listOf()
        }
    }

    override fun setup(list: List<Shield>, ctx: Context) {
        val newList = mutableListOf<Shield>()
        list.map {
            newList.add(it.setDrawable(ctx))
        }
        items = newList.toSet().toList()
        searchList = items
    }
    override fun search(regex: Regex, viewModel: ItemViewModel<Shield>): List<Shield> {
        var finalList: List<Shield>?
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
            finalList = nameList + subList + subList2
        }
        return finalList?:listOf()
    }
}