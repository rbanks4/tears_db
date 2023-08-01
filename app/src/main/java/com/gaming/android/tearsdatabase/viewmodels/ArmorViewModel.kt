package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Armor

private const val ARMOR_ITEM = "armor"

class ArmorViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Armor> {
    override var items: List<Armor>?
        get() = savedStateHandle.get<List<Armor>>(ARMOR_ITEM)
        set(value) = savedStateHandle.set(ARMOR_ITEM, value)

    override var searchList: List<Armor>?
        get() = savedStateHandle.get<List<Armor>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}