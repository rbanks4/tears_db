package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.Effect
import com.gaming.android.tearsdatabase.models.RoastedFood
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ROASTED_FOOD_ITEM = "roasted"

@HiltViewModel
class RoastedFoodViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<RoastedFood> {
    override var items: List<RoastedFood>?
        get() = savedStateHandle.get<List<RoastedFood>>(ROASTED_FOOD_ITEM)
        set(value) = savedStateHandle.set(ROASTED_FOOD_ITEM, value)

    override var searchList: List<RoastedFood>?
        get() = savedStateHandle.get<List<RoastedFood>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    private val _foods: MutableStateFlow<List<RoastedFood>> = MutableStateFlow(emptyList())
    val roastedFood: StateFlow<List<RoastedFood>>
        get() = _foods.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val fetchedItems = repo.fetchRoasted()
                _foods.value = fetchedItems
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
    }

    override fun sort(choice: Int, list: List<RoastedFood>?): List<RoastedFood>? {
        return when (choice) {
            SORT_BUYING_INC ->
                list?.sortedByDescending { it.buying_price }
            SORT_BUYING_DEC ->
                list?.sortedBy { it.buying_price }
            SORT_SELLING_INC ->
                list?.sortedBy { it.selling_price }
            SORT_SELLING_DEC ->
                list?.sortedByDescending { it.selling_price }
            SORT_ID_INC ->
                list?.sortedBy { it.recipe_no }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<RoastedFood>): List<RoastedFood> {
        var finalList: List<RoastedFood>? = listOf()
        viewModel.items?.let { list ->
            val nameList = list.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list.filter {
                if (it.sub_type.isNotEmpty())
                    it.sub_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val subList2 = list.filter {
                if (it.effect_type.isNotEmpty())
                    it.effect_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            finalList = nameList + subList + subList2
        }
        return finalList?: listOf()
    }
}