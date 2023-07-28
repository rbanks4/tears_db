package com.gaming.android.tearsdatabase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Meal

private const val TAG = "MealsViewModel"
private const val MEALS_ITEM = "meals"
private const val SEARCH_LIST = "search_list"
private const val SEARCH_STRING = "search_string"

class MealsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var meals: List<Meal>?
        get() = savedStateHandle.get<List<Meal>>(MEALS_ITEM)
        set(value) = savedStateHandle.set(MEALS_ITEM, value)

    var searchList: List<Meal>?
        get() = savedStateHandle.get<List<Meal>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}