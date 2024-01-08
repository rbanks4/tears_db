package com.gaming.android.tearsdatabase.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.SORT_DEF_DEC
import com.gaming.android.tearsdatabase.SORT_DEF_INC
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.Armor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARMOR_ITEM = "armor"

@HiltViewModel
class ArmorViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(),
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

    private val _armor: MutableStateFlow<List<Armor>> = MutableStateFlow(emptyList())
    val armor: StateFlow<List<Armor>>
        get() = _armor.asStateFlow()
    init {
        viewModelScope.launch {
            try {
                val fetchedItems = repo.fetchArmor()
                _armor.value = fetchedItems
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
    }

    override fun sort(choice: Int, list: List<Armor>?): List<Armor>? {
        return when (choice) {
            SORT_DEF_INC ->
                list?.sortedBy { it.base_defense }
                    ?.sortedBy { it.star_one }
            SORT_DEF_DEC ->
                list?.sortedByDescending { it.base_defense }
                    ?.sortedByDescending { it.star_one }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Armor>): List<Armor> {
        var finalList: List<Armor>?
        viewModel.items.let { list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list.filter {
                if (it.set_name.isNotEmpty())
                    it.set_name.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val subList2 = list.filter {
                var found = false
                if(it.set_bonus.isEmpty()) {
                    false
                } else {
                    for (i in it.set_bonus) {
                        found = i.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                        if (found) found
                    }
                    found
                }
            }
            val subList3 = list.filter {
                var found = false
                if(it.effect.isEmpty()) {
                    false
                } else {
                    for (i in it.effect) {
                        found = i.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                        if (found) found
                    }
                    found
                }
            }
            finalList = nameList + subList + subList2 + subList3
        }
        return finalList?:listOf()
    }
}