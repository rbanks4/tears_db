package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Shield

private const val SHIELDS_ITEM = "shields"
class ShieldsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Shield> {
    override var items: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SHIELDS_ITEM)
        set(value) = savedStateHandle.set(SHIELDS_ITEM, value)

    override var searchList: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}