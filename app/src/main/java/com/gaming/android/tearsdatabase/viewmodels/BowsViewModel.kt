package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.BattleItem
import com.gaming.android.tearsdatabase.models.Bow
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

private const val BOWS_ITEM = "bows"
@HiltViewModel
class BowsViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(),
    ItemViewModel<Bow>, BattleItemViewModel<Bow> {
    override var items: List<Bow>?
        get() = savedStateHandle.get<List<Bow>>(BOWS_ITEM)?.toSet()?.sortedBy { it.compendium_no }
        set(value) = savedStateHandle.set(BOWS_ITEM, value)

    override var searchList: List<Bow>?
        get() = savedStateHandle.get<List<Bow>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    private val _bows: MutableStateFlow<List<Bow>> = MutableStateFlow(emptyList())
    val bows: StateFlow<List<Bow>>
        get() = _bows.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val fetchedItems = repo.fetchBows()
                _bows.value = fetchedItems
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
    }

    override fun sort(choice: Int, list: List<Bow>?): List<Bow>? {
        return when (choice) {
            SORT_DAMAGE_DEC ->
                list?.sortedByDescending { it.base_attack }
            SORT_DAMAGE_INC ->
                list?.sortedBy { it.base_attack }
            SORT_DURABILITY_DEC ->
                list?.sortedByDescending { it.durability }
            SORT_DURABILITY_INC ->
                list?.sortedBy { it.durability }
            SORT_DRAWING_TIME_DEC ->
                list?.sortedByDescending { it.drawing_time }
            SORT_DRAWING_TIME_INC ->
                list?.sortedBy { it.drawing_time }
            SORT_RELOAD_TIME_DEC ->
                list?.sortedByDescending { it.reload_time }
            SORT_RELOAD_TIME_INC ->
                list?.sortedBy { it.reload_time }
            SORT_RANGE_DEC ->
                list?.sortedByDescending { it.range }
            SORT_RANGE_INC ->
                list?.sortedBy { it.range }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Bow>): List<Bow> {
        val finalList = searchItemsWithSubtype(viewModel.items, regex.toString())
        return finalList?: listOf()
    }
}