package com.gaming.android.tearsdatabase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Bow

private const val TAG = "BowsViewModel"
private const val BOWS_ITEM = "bows"
private const val SEARCH_LIST = "search_list"
private const val SEARCH_STRING = "search_string"
class BowsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var bows: List<Bow>?
        get() = savedStateHandle.get<List<Bow>>(BOWS_ITEM)
        set(value) = savedStateHandle.set(BOWS_ITEM, value)

    var searchList: List<Bow>?
        get() = savedStateHandle.get<List<Bow>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}