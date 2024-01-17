package com.gaming.android.tearsdatabase.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.data.SampleData
import javax.inject.Inject

class MealsViewModelTest {
    @Inject
    lateinit var repo: ItemRepository

    @org.junit.jupiter.api.Test
    fun search() {
        repo.fetchMeals()
        var vm = MealsViewModel(repo = repo, SavedStateHandle())
        vm.update(SampleData.meals)
        var meals = vm.search("cookcrab".toRegex(), vm)
        assert(meals.size == 4) { println("wee snaw") }
        //"(Fortified Pumpkin or Sun Pumpkin or Swift Carrot or Endura Carrot) and Hylian Rice and Rock Salt and Goat Butter"
    }
}