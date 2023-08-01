package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Material

private const val TAG = "MaterialsViewModel"
private const val MATERIALS_ITEM = "materials"

class MaterialsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Material> {
    override var items: List<Material>?
        get() = savedStateHandle.get<List<Material>>(MATERIALS_ITEM)
        set(value) = savedStateHandle.set(MATERIALS_ITEM, value)

    override var searchList: List<Material>?
        get() = savedStateHandle.get<List<Material>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)
}