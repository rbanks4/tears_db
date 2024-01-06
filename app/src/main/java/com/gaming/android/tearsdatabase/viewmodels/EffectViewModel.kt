package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.Effect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val EFFECT_ITEM = "effect"
private const val MAP = "eff_map"
@HiltViewModel
class EffectViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(),
    ItemViewModel<Effect> {
    override var items: List<Effect>?
        get() = savedStateHandle.get<List<Effect>>(EFFECT_ITEM)
        set(value) = savedStateHandle.set(EFFECT_ITEM, value)

    var map: Map<String, Effect>?
        get() = savedStateHandle.get<Map<String, Effect>>(MAP)
        set(value) = savedStateHandle.set(MAP, value)

    override var searchList: List<Effect>?
        get() = savedStateHandle.get<List<Effect>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    private val _effects: MutableStateFlow<List<Effect>> = MutableStateFlow(emptyList())
    val effects: StateFlow<List<Effect>>
        get() = _effects.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val fetchedItems = repo.fetchEffects()
                _effects.value = fetchedItems
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
    }

    override fun setup(list: List<Effect>, findDrawable: (Effect) -> Effect) {
        super.setup(list) { findDrawable(it) }
        val newMap = mutableMapOf<String, Effect>()
        list.map {
            if(newMap.containsKey(it.name)) {
                when(it.level) {
                    1 -> newMap[it.name]?.effect_level1 = it
                    2 -> newMap[it.name]?.effect_level2 = it
                    3 -> newMap[it.name]?.effect_level3 = it
                }
            } else {
                newMap[it.name] = it
            }
        }
        map = newMap
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Effect>): List<Effect> {
        return viewModel.items?: listOf()
    }

    override fun sort(choice: Int, list: List<Effect>?): List<Effect>? {
        return list
    }
}