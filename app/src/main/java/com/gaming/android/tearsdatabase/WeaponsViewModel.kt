package com.gaming.android.tearsdatabase

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class WeaponsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    var TAG = "WeaponsViewModel"
    var WEAPONS_ITEM = "weapons"
    init {
        Log.d(TAG, "WeaponsViewModel instance created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "WeaponsViewModel instance about to be destroyed")
    }

    fun setWeapons(weaponsList: List<Weapon>) {
        savedStateHandle[WEAPONS_ITEM] = weaponsList
    }

    fun getWeapons(): List<Weapon>? {
        return savedStateHandle.get<List<Weapon>>(WEAPONS_ITEM)
    }
}