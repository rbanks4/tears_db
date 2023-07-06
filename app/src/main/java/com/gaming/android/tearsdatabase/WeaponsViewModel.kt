package com.gaming.android.tearsdatabase

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "WeaponsViewModel"
private const val WEAPONS_ITEM = "weapons"
private const val SEARCH_LIST = "search_list"
private const val SEARCH_STRING = "search_string"
class WeaponsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var weapons: List<Weapon>?
        get() = savedStateHandle.get<List<Weapon>>(WEAPONS_ITEM)
        set(value) = savedStateHandle.set(WEAPONS_ITEM, value)

    var searchList: List<Weapon>?
        get() = savedStateHandle.get<List<Weapon>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    init {
        Log.d(TAG, "WeaponsViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "WeaponsViewModel instance about to be destroyed")
    }
}