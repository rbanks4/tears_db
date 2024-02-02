package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gaming.android.tearsdatabase.SORT_ID_INC
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Meal
import com.gaming.android.tearsdatabase.models.submodels.CookId
import com.gaming.android.tearsdatabase.models.submodels.CookId.*
import com.gaming.android.tearsdatabase.models.submodels.EffectId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MEALS_ITEM = "meals"

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val repo: ItemRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(),
    ItemViewModel<Meal> {
    override var items: List<Meal>?
        get() = savedStateHandle.get<List<Meal>>(MEALS_ITEM)
        set(value) = savedStateHandle.set(MEALS_ITEM, value)

    override var searchList: List<Meal>?
        get() = savedStateHandle.get<List<Meal>>(SEARCH_LIST)
        set(value) = savedStateHandle.set(SEARCH_LIST, value)

    override var searchString: String?
        get() = savedStateHandle.get<String>(SEARCH_STRING)
        set(value) = savedStateHandle.set(SEARCH_STRING, value)

    private val _meals: MutableStateFlow<List<Meal>> = MutableStateFlow(emptyList())
    val meals: StateFlow<List<Meal>>
        get() = _meals.asStateFlow()

    private val search = MutableStateFlow(savedStateHandle.get<String>(SEARCH_STRING) ?: "")

    val liveMeals: LiveData<List<Meal>> = search.flatMapLatest {
        flowOf(search(it.toRegex(), this).toSet().toList())
    }.asLiveData()

    private var idMap: Map<Int, Material> = mutableMapOf()
    private var cookIdMap: Map<Int, List<Material>> = mutableMapOf()

    init {
        viewModelScope.launch {
            try {
                val fetchedItems = repo.fetchMeals()
                _meals.value = fetchedItems
                search.value = ""
            } catch (e: Exception) {
                println("Failed to fetch items ${e.message}")
            }
        }
    }

    override fun sort(choice: Int, list: List<Meal>?): List<Meal>? {
        return when (choice) {
            SORT_ID_INC ->
                list?.sortedBy { it.recipe_no }
            else -> listOf()
        }
    }

    fun setSearch(query: String) {
        search.value = ".*$query.*"
    }

    fun updateWithMaterials(materials: List<Material>) {
        idMap = materials.associateBy(Material::_id)
        cookIdMap = materials.groupBy { it.cook_id }
    }

    fun find(id: Int): Material? {
        return idMap[id]
    }

    fun findByCookId(cookId: Int): List<Material>? {
        return cookIdMap[cookId]
    }

    fun findMaterialsForRecipe(pair: Pair<Int, Int>): List<Material> {
        var materials = listOf<Material>()
        pair.let { (cookId, id) ->
            when(cookId) {
                Other.id -> {
                    find(id)?.let {
                        materials = listOf(it)
                    }
                }
                Insect.id -> {
                    if (id == EffectId.None.id) {
                        materials = findByCookId(cookId)?: emptyList()
                    } else {
                        materials = findByCookId(cookId)?.filter { it.effect_id == id }?: emptyList()
                    }
                }
                else -> {
                    materials = findByCookId(cookId)?: emptyList()
                }
            }
        }
        return materials
    }

//    override fun setup(list: List<Meal>, findDrawable: (Meal) -> Meal) {
//        super.setup(list, findDrawable)
//        var newList: List<Meal>
//        var recipeList: MutableList<Map<CookId, Int>> = mutableListOf()
//        list.forEach { meal ->
//            meal.recipe.forEach { recipeItems ->
//                recipeItems.zipWithNext().forEach { (cookId, id) ->
//                    when(cookId) {
//                        Other.id -> {
//                            recipeList.add(mapOf(Other to id))
//                        }
//                    }
////                    var cookId = CookId.entries.filter { recipeName.contains(it.name) }.first()
////                    var effectId = EffectId.entries.filter { recipeName.contains(it.name) }.first()
////                    recipeList.add(mapOf(cookId to effectId))
//                }
//            }
//        }
//    }

    override fun search(regex: Regex, viewModel: ItemViewModel<Meal>): List<Meal> {
        var finalList: List<Meal>?
        viewModel.items.let { list ->
            val nameList = list!!.filter {
                it.name.lowercase().matches(".*$regex.*".toRegex())
            }

//            val subList = list!!.filter {
//                var matches: Boolean = false
//                it.recipe.forEach {
//                    it.forEach{
//                        if(!matches) {
//                            matches = it
//                                .lowercase()
//                                .replace("\n", "")
//                                .matches(".*$regex.*".toRegex())
//                        }
//                    }
//                }
//
//                matches
//            }

            finalList = nameList //+ subList
        }

        //searchList = finalList?: listOf()
        return finalList?: listOf()
    }
}