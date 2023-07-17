package com.gaming.android.tearsdatabase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Shield

private const val TAG = "ShieldsViewModel"
private const val SHIELDS_ITEM = "shields"
private const val SEARCH_LIST = "search_list"
private const val SEARCH_STRING = "search_string"
class ShieldsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var shields: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SHIELDS_ITEM)
        set(value) = savedStateHandle.set(SHIELDS_ITEM, value)

    var searchList: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}