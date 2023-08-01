package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Bow

private const val BOWS_ITEM = "bows"
class BowsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Bow> {
    override var items: List<Bow>?
        get() = savedStateHandle.get<List<Bow>>(BOWS_ITEM)
        set(value) = savedStateHandle.set(BOWS_ITEM, value)

    override var searchList: List<Bow>?
        get() = savedStateHandle.get<List<Bow>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}