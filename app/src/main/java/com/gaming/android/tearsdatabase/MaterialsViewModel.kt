package com.gaming.android.tearsdatabase

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Material

private const val TAG = "MaterialsViewModel"
private const val MATERIALS_ITEM = "weapons"
private const val SEARCH_LIST = "search_list"
private const val SEARCH_STRING = "search_string"

class MaterialsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var materials: List<Material>?
        get() = savedStateHandle.get<List<Material>>(MATERIALS_ITEM)
        set(value) = savedStateHandle.set(MATERIALS_ITEM, value)

    var searchList: List<Material>?
        get() = savedStateHandle.get<List<Material>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}