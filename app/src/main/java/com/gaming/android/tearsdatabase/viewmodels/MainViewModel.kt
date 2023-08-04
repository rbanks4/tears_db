package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val SELECTED_NAV_ITEM = "selected"
class MainViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var navItem: String?
        get() = savedStateHandle.get<String>(SELECTED_NAV_ITEM)
        set(value) = savedStateHandle.set(SELECTED_NAV_ITEM, value)
}