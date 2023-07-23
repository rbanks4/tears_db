package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.DataSource

data class Meal(
    val actor_name: String,
    val name: String,
    val recipe_no: Int,
    val recipe: String,
    val bonus_heart: Int,
    val bonus_level: Int,
    val bonus_time: Int
        ) {
    @DrawableRes
    var image: Int = R.drawable.mushroom_skewer


    fun setDrawable(@DrawableRes int: Int): Meal {
        image = int
        return this
    }

    fun setDrawable(ctx: Context): Meal {
        findDrawable(ctx)
        return this
    }

    fun findDrawable(ctx: Context) {
        image = DataSource.loadWeaponImage(name, ctx)
    }
}