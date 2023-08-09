package com.gaming.android.tearsdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.models.Effect

private const val EFFECT_ITEM = "effect"
class EffectViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Effect> {
    override var items: List<Effect>?
        get() = savedStateHandle.get<List<Effect>>(EFFECT_ITEM)
        set(value) = savedStateHandle.set(EFFECT_ITEM, value)

    override var searchList: List<Effect>?
        get() = savedStateHandle.get<List<Effect>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    override fun search(regex: Regex, viewModel: ItemViewModel<Effect>): List<Effect> {
        return viewModel.items?: listOf()
    }

    override fun sort(choice: Int, list: List<Effect>?): List<Effect>? {
        return list
    }

    override fun getImage(item: Effect, ctx: Context): Effect {
        return item.setDrawable(ctx)
    }
}