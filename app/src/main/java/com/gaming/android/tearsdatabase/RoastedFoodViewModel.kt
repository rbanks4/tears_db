package com.gaming.android.tearsdatabase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.RoastedFood

private const val TAG = "RoastedFoodViewModel"
private const val ROASTED_FOOD_ITEM = "roasted"
private const val SEARCH_LIST = "search_list"
private const val SEARCH_STRING = "search_string"
class RoastedFoodViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var roastedFood: List<RoastedFood>?
        get() = savedStateHandle.get<List<RoastedFood>>(ROASTED_FOOD_ITEM)
        set(value) = savedStateHandle.set(ROASTED_FOOD_ITEM, value)

    var searchList: List<RoastedFood>?
        get() = savedStateHandle.get<List<RoastedFood>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}