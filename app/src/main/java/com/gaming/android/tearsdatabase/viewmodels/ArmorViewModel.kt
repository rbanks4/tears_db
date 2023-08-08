package com.gaming.android.tearsdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.SORT_DEF_DEC
import com.gaming.android.tearsdatabase.SORT_DEF_INC
import com.gaming.android.tearsdatabase.models.Armor
import com.gaming.android.tearsdatabase.models.Item

private const val ARMOR_ITEM = "armor"

class ArmorViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Armor> {
    override var items: List<Armor>?
        get() = savedStateHandle.get<List<Armor>>(ARMOR_ITEM)
        set(value) = savedStateHandle.set(ARMOR_ITEM, value)

    override var searchList: List<Armor>?
        get() = savedStateHandle.get<List<Armor>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    override fun setup(list: List<Armor>, ctx: Context) {
        val newList = mutableListOf<Armor>()
        list.map {
            newList.add(it.setDrawable(ctx))
        }
        items = newList.toSet().toList()
        searchList = items
    }

    override fun getImage(item: Armor, ctx: Context): Armor {
        return item.setDrawable(ctx)
    }

    override fun sort(choice: Int, list: List<Armor>?): List<Armor>? {
        return when (choice) {
            SORT_DEF_INC ->
                list?.sortedBy { it.base_defense }
                    ?.sortedBy { it.star_one }
            SORT_DEF_DEC ->
                list?.sortedByDescending { it.base_defense }
                    ?.sortedByDescending { it.star_one }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Armor>): List<Armor> {
        var finalList: List<Armor>?
        viewModel.items.let { list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list.filter {
                if (it.set_name.isNotEmpty())
                    it.set_name.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val subList2 = list.filter {
                if (it.set_bonus.isNotEmpty())
                    it.set_bonus.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val subList3 = list.filter {
                if (it.effect.isNotEmpty())
                    it.effect.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            finalList = nameList + subList + subList2 + subList3
        }
        return finalList?:listOf()
    }
}