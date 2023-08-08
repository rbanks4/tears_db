package com.gaming.android.tearsdatabase.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.models.Material

private const val TAG = "MaterialsViewModel"
private const val MATERIALS_ITEM = "materials"

class MaterialsViewModel(private val savedStateHandle: SavedStateHandle): ViewModel(),
    ItemViewModel<Material> {
    override var items: List<Material>?
        get() = savedStateHandle.get<List<Material>>(MATERIALS_ITEM)
        set(value) = savedStateHandle.set(MATERIALS_ITEM, value)

    override var searchList: List<Material>?
        get() = savedStateHandle.get<List<Material>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    override fun getImage(item: Material, ctx: Context): Material {
        return item.setDrawable(ctx)
    }

    override fun sort(choice: Int, list: List<Material>?): List<Material>? {
        return when (choice) {
            SORT_HP_DEC ->
                list?.sortedByDescending { it.hp_recover }
                    ?.filter{ it.hp_recover != 0 && it.hp_recover != null }
            SORT_DAMAGE_INC ->
                list?.sortedBy { it.additional_damage }
                    ?.filter{ it.additional_damage != -1 }
            SORT_DAMAGE_DEC ->
                list?.sortedByDescending { it.additional_damage }
                    ?.filter{ it.additional_damage != -1 }
            SORT_SELLING_DEC ->
                list?.sortedByDescending { it.selling_price }
                    ?.filter{ it.selling_price != null }
            SORT_SELLING_INC ->
                list?.sortedBy { it.selling_price }
                    ?.filter{ it.selling_price != null }
            SORT_BUYING_DEC ->
                list?.sortedByDescending { it.buying_price?.toInt() }
                    ?.filter{ it.buying_price != null }
            SORT_BUYING_INC ->
                list?.sortedBy { it.buying_price }
                    ?.filter{ it.buying_price != null }
            else -> listOf()
        }
    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Material>): List<Material> {
        var finalList: List<Material>?
        viewModel.items.let { list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }
            val subList = list!!.filter {
                if (it.sub_type.isNotEmpty())
                    it.sub_type.lowercase().replace("\n", "").matches(".*$regex.*".toRegex())
                else false
            }
            val effects = list!!.filter {
                if (it.effect_type.isNotEmpty())
                    it.effect_type.lowercase().matches(".*$regex.*".toRegex())
                else false
            }
            val color = list!!.filter {
                if (it.dye_color.isNotEmpty())
                    it.dye_color.lowercase().matches(".*$regex.*".toRegex())
                else false
            }

            finalList = nameList + subList + effects + color
        }
        return finalList?: listOf()
    }
}