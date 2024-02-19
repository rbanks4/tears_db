package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.SORT_DURABILITY_DEC
import com.gaming.android.tearsdatabase.SORT_DURABILITY_INC
import com.gaming.android.tearsdatabase.SORT_SLIPPERINESS_DEC
import com.gaming.android.tearsdatabase.SORT_SLIPPERINESS_INC
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.viewmodels.interfaces.BattleItemViewModel
import com.gaming.android.tearsdatabase.viewmodels.interfaces.ItemViewModel
import com.gaming.android.tearsdatabase.viewmodels.interfaces.SEARCH_LIST
import com.gaming.android.tearsdatabase.viewmodels.interfaces.SEARCH_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SHIELDS_ITEM = "shields"

@HiltViewModel
class ShieldsViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Shield>, BattleItemViewModel<Shield> {
    override var items: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SHIELDS_ITEM)?.toSet()?.sortedBy { it.compendium }
        set(value) = savedStateHandle.set(SHIELDS_ITEM, value)

    override var searchList: List<Shield>?
        get() = savedStateHandle.get<List<Shield>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    private val _shields: MutableStateFlow<List<Shield>> = MutableStateFlow(emptyList())
    val shields: StateFlow<List<Shield>>
        get() = _shields.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val fetchedItems = repo.fetchShields()
                _shields.value = fetchedItems
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
    }

    override fun sort(choice: Int, list: List<Shield>?): List<Shield>? {
        return when (choice) {
            SORT_DURABILITY_DEC ->
                list?.sortedByDescending { it.durability }
            SORT_DURABILITY_INC ->
                list?.sortedBy { it.durability }
            SORT_SLIPPERINESS_DEC ->
                list?.sortedBy { it.shield_surfing_friction }
            SORT_SLIPPERINESS_INC ->
                list?.sortedByDescending { it.shield_surfing_friction }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Shield>): List<Shield> {
        val finalList = searchItemsWithSubtype(viewModel.items, regex.toString())
        return finalList?: listOf()
    }
}