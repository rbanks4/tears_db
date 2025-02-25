package com.gaming.android.tearsdatabase

import androidx.lifecycle.SavedStateHandle
import com.gaming.android.tearsdatabase.api.ItemRepository
import com.gaming.android.tearsdatabase.api.response.MaterialsAndMealsResponse
import com.gaming.android.tearsdatabase.models.Armor
import com.gaming.android.tearsdatabase.models.Bow
import com.gaming.android.tearsdatabase.models.Effect
import com.gaming.android.tearsdatabase.models.Material
import com.gaming.android.tearsdatabase.models.Meal
import com.gaming.android.tearsdatabase.models.RoastedFood
import com.gaming.android.tearsdatabase.models.Shield
import com.gaming.android.tearsdatabase.models.Weapon
import com.gaming.android.tearsdatabase.models.submodels.CookId
import com.gaming.android.tearsdatabase.models.submodels.EffectId
import com.gaming.android.tearsdatabase.viewmodels.MealsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MealsViewModelTest {

    private lateinit var viewModel: MealsViewModel

    @Before
    fun setUp() {
        viewModel = MealsViewModel(FakeItemRepository(), SavedStateHandle())
        viewModel.updateWithMaterials(
            listOf(
                createMaterial(_id = 1, name = "Spicy Pepper", cook_id = CookId.Other.id, effect_id = EffectId.Spicy.id),
                createMaterial(_id = 2, name = "Cool Safflina", cook_id = CookId.Other.id, effect_id = EffectId.Chilly.id),
                createMaterial(_id = 3, name = "Voltfruit", cook_id = CookId.Fruit.id, effect_id = EffectId.Electro.id),
                createMaterial(_id = 4, name = "Sunshroom", cook_id = CookId.Mushroom.id, effect_id = EffectId.Spicy.id)
            )
        )
    }

    @Test
    fun findMaterialsForRecipe_should_return_correct_RecipePair() {
        // Arrange
        val inputPair = Pair(CookId.Other.id, EffectId.Spicy.id)

        // Act
        val result = viewModel.findMaterialsForRecipe(inputPair)

        // Assert
        val expectedMaterials = listOf(
            createMaterial(_id = 1, name = "Spicy Pepper", cook_id = CookId.Other.id, effect_id = EffectId.Spicy.id),
            createMaterial(_id = 4, name = "Sunshroom", cook_id = CookId.Mushroom.id, effect_id = EffectId.Spicy.id)
        )
        val expectedText = EffectId.Spicy.name

        assertEquals(expectedMaterials, result.list)
        assertEquals(expectedText, result.name)
    }
}

fun createMaterial(_id: Int, name: String, cook_id: Int, effect_id: Int): Material {
    return Material(
        _id = _id,
        name = "Spicy Pepper",
        cook_id = cook_id,
        effect_id = effect_id,
        additional_damage = 0,
        boost_critical_cook = 0,
        dye_color = "",
        sheild_bash_damage = 0,
        selling_price = 0,
        sub_type = "",
        boost_max_stamina = 0,
        boost_hp_recover = 0,
        boost_effective_time = 0,
        boost_max_heart = 0,
        additional_damage_rate_arrow = 0,
        buying_price = "",
        effect_level = 0,
        hp_recover = 0,
        effect_time = 0,
        effect_type = ""
    )
}

// Mock repository for testing
class FakeItemRepository : ItemRepository {
    // Add necessary mock implementations here if required.
    override suspend fun fetchArmor(): List<Armor> {
        return listOf()
    }

    override suspend fun fetchBows(): List<Bow> {
        return listOf()
    }

    override suspend fun fetchEffects(): List<Effect> {
        return listOf()
    }

    override suspend fun fetchMaterials(): List<Material> {
        return listOf()
    }

    override suspend fun fetchMeals(): List<Meal> {
        return listOf()
    }

    override suspend fun fetchRoasted(): List<RoastedFood> {
        return listOf()
    }

    override suspend fun fetchShields(): List<Shield> {
        return listOf()
    }

    override suspend fun fetchWeapons(): List<Weapon> {
        return listOf()
    }

    override suspend fun fetchMaterialsAndMeals(): MaterialsAndMealsResponse {
        return MaterialsAndMealsResponse(listOf(), listOf())
    }
}
