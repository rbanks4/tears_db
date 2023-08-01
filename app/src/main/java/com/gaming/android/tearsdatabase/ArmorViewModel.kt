package com.gaming.android.tearsdatabase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Armor

private const val TAG = "ArmorViewModel"
private const val ARMOR_ITEM = "armor"
private const val SEARCH_LIST = "search_list"
private const val SEARCH_STRING = "search_string"

class ArmorViewModel(private val savedStateHandle: SavedStateHandle): ViewModel()  {
    var armor: List<Armor>?
        get() = savedStateHandle.get<List<Armor>>(ARMOR_ITEM)
        set(value) = savedStateHandle.set(ARMOR_ITEM, value)

    var searchList: List<Armor>?
        get() = savedStateHandle.get<List<Armor>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}