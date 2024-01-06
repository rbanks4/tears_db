package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.Bow
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
        var finalList: List<Bow>?
        viewModel.items.let { list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list!!.filter {
                if (it.sub_type.isNotEmpty())
                    it.sub_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val subList2 = list!!.filter {
                if (it.sub_type2.isNotEmpty())
                    it.sub_type2.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val other = list!!.filter {
                if (it.other.isNotEmpty())
                    it.other.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            finalList = nameList + subList + subList2 + other
        }
        return finalList?: listOf()
    }
}