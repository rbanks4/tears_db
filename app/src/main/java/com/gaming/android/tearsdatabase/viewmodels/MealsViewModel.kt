package com.gaming.android.tearsdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.SORT_ID_INC
import com.gaming.android.tearsdatabase.models.Meal

private const val MEALS_ITEM = "meals"

class MealsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Meal> {
    override var items: List<Meal>?
        get() = savedStateHandle.get<List<Meal>>(MEALS_ITEM)
        set(value) = savedStateHandle.set(MEALS_ITEM, value)

    override var searchList: List<Meal>?
        get() = savedStateHandle.get<List<Meal>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    override fun getImage(item: Meal, ctx: Context): Meal {
        return item.setDrawable(ctx)
    }
    override fun sort(choice: Int, list: List<Meal>?): List<Meal>? {
        return when (choice) {
            SORT_ID_INC ->
                list?.sortedBy { it.recipe_no }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Meal>): List<Meal> {
        var finalList: List<Meal>?
        viewModel.items.let { list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list!!.filter {
                if (it.recipe.isNotEmpty())
                    it.recipe.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            finalList = nameList + subList
        }
        return finalList?:listOf()
    }
}