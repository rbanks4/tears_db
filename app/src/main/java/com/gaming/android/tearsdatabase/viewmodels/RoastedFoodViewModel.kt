package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.RoastedFood

private const val ROASTED_FOOD_ITEM = "roasted"
class RoastedFoodViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<RoastedFood> {
    override var items: List<RoastedFood>?
        get() = savedStateHandle.get<List<RoastedFood>>(ROASTED_FOOD_ITEM)
        set(value) = savedStateHandle.set(ROASTED_FOOD_ITEM, value)

    override var searchList: List<RoastedFood>?
        get() = savedStateHandle.get<List<RoastedFood>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}