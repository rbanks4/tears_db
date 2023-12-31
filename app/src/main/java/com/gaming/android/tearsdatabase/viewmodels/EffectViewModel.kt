package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Effect

private const val EFFECT_ITEM = "effect"
private const val MAP = "eff_map"
class EffectViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
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