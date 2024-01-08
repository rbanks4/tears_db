package com.gaming.android.tearsdatabase.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.SORT_DAMAGE_DEC
import com.gaming.android.tearsdatabase.SORT_DAMAGE_INC
import com.gaming.android.tearsdatabase.SORT_DURABILITY_DEC
import com.gaming.android.tearsdatabase.SORT_DURABILITY_INC
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.Weapon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "WeaponsViewModel"
private const val WEAPONS_ITEM = "weapons"

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(),
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

    private val _weapons: MutableStateFlow<List<Weapon>> = MutableStateFlow(emptyList())
    val weapons: StateFlow<List<Weapon>>
        get() = _weapons.asStateFlow()

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "WeaponsViewModel instance about to be destroyed")
    }

    init {
        viewModelScope.launch {
            try {
                val fetchedItems = repo.fetchWeapons()
                _weapons.value = fetchedItems
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
    }

    override fun sort(choice: Int, list: List<Weapon>?): List<Weapon>? {
        return when (choice) {
            SORT_DAMAGE_DEC ->
                list?.sortedByDescending { it.shown_attack }
            SORT_DAMAGE_INC ->
                list?.sortedBy { it.shown_attack }
            SORT_DURABILITY_DEC ->
                list?.sortedByDescending { it.durability }
            SORT_DURABILITY_INC ->
                list?.sortedBy { it.durability }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Weapon>): List<Weapon> {
        var finalList: List<Weapon>? = listOf()
        viewModel.items?.let { list ->
            val nameList = list.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list.filter {
                var found = false
                if(it.sub_type.isEmpty()) {
                    false
                } else {
                    for (i in it.sub_type) {
                        found = i.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                        if (found) found
                    }
                    found
                }
            }
            val subList2 = list.filter {
                var found = false
                if(it.sub_type2.isEmpty()) {
                    false
                } else {
                    for (i in it.sub_type2) {
                        found = i.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                        if (found) found
                    }
                    found
                }
            }

            finalList = nameList + subList + subList2
        }
        return finalList?: listOf()
    }
}