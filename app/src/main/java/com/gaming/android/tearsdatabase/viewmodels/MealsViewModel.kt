package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
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
}