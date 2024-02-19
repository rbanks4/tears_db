package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.*
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.api.response.MaterialsAndMealsResponse
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.viewmodels.interfaces.ItemViewModel
import com.gaming.android.tearsdatabase.viewmodels.interfaces.SEARCH_LIST
import com.gaming.android.tearsdatabase.viewmodels.interfaces.SEARCH_STRING
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MaterialsViewModel"
private const val MATERIALS_ITEM = "materials"

@HiltViewModel
class MaterialsViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(),
    ItemViewModel<Material> {
    override var items: List<Material>?
        get() = savedStateHandle.get<List<Material>>(MATERIALS_ITEM)?.toSet()?.sortedBy { it._id }
        set(value) = savedStateHandle.set(MATERIALS_ITEM, value)

    override var searchList: List<Material>?
        get() = savedStateHandle.get<List<Material>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    private val _response: MutableStateFlow<MaterialsAndMealsResponse> =
        MutableStateFlow(MaterialsAndMealsResponse(
            emptyList(),
            emptyList()
        ))

    val response: StateFlow<MaterialsAndMealsResponse>
        get() = _response.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val fetchedMAndM = repo.fetchMaterialsAndMeals()
                _response.value = fetchedMAndM
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
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