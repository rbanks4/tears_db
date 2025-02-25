package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.models.submodels.CookId
import com.gaming.android.tearsdatabase.models.submodels.EffectId

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
    val recipeList: List<List<Pair<CookId, EffectId>>>
        get() = recipe.map { lists ->
            lists.chunked(2).map { (cookId, effectId) ->
                Pair(CookId.fromInt(cookId)?:CookId.Other, EffectId.fromInt(effectId)?:EffectId.None)
            }
        }

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