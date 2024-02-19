package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Meal(
    val _id: Int,
    val actor_name: String,
    override val name: String,
    val recipe_no: Int,
    val recipe: List<List<Int>>,
    val bonus_heart: Int?,
    val bonus_level: Int?,
    val bonus_time: Int?
        ): Item<Meal> {
    @DrawableRes
    override var image: Int = R.drawable.mushroom_skewer
    var recipeIds: MutableList<Pair<Int, Int>>? = mutableListOf()

    override fun get(): Meal {
        return this
    }


    override fun setDrawable(@DrawableRes int: Int): Meal {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Meal {
        findDrawable(ctx)
        return this
    }
}