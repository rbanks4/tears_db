package com.gaming.android.tearsdatabase.models.submodels

import androidx.annotation.StringRes
import com.gaming.android.tearsdatabase.R

enum class CookId(
    val id: Int,
    @StringRes val cookingName: Int
) {
    Foreign(0, R.string.c_foreign),
    Fish(1, R.string.c_fish),
    Mushroom(2, R.string.c_mushroom),
    Crab(3, R.string.c_crab),
    Snail(4, R.string.c_snail),
    Plant(5, R.string.c_plant),
    Fruit(6, R.string.c_fruit),
    Meat(7, R.string.c_meat),
    Ore(8, R.string.c_ore),
    Enemy(9, R.string.c_enemy),
    Insect(10, R.string.c_insect),
    //Veggie(11),
    Other(12, R.string.c_other),
    Dragon(13, R.string.c_dragon);

    companion object {
        private val map = entries.associateBy(CookId::id)
        fun fromInt(id: Int) = map[id]
    }
}

