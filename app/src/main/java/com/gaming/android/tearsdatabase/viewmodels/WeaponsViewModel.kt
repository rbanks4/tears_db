package com.gaming.android.tearsdatabase.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Weapon

private const val TAG = "WeaponsViewModel"
private const val WEAPONS_ITEM = "weapons"
class WeaponsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Weapon> {
    override var items: List<Weapon>?
        get() = savedStateHandle.get<List<Weapon>>(WEAPONS_ITEM)
        set(value) = savedStateHandle.set(WEAPONS_ITEM, value)

    override var searchList: List<Weapon>?
        get() = savedStateHandle.get<List<Weapon>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "WeaponsViewModel instance about to be destroyed")
    }
}