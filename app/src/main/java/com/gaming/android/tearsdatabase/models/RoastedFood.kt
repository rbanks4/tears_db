package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.DataSource

data class RoastedFood(
    val actor_name: String,
    val name: String,
    val recipe_no: Int,
    val buying_price: Int,
    val selling_price: Int,
    val effect_level: Int,
    val effect_type: String,
    val effect_time: Int,
    val hit_point_counter: Int,
    val sub_type: String,
    val shield_bash_damage: Int?,
    val color: String
) {
    @DrawableRes
    var image: Int = R.drawable.hard_boiled_egg


    fun setDrawable(@DrawableRes int: Int): RoastedFood {
        image = int
        return this
    }

    fun setDrawable(ctx: Context): RoastedFood {
        findDrawable(ctx)
        return this
    }

    fun findDrawable(ctx: Context) {
        image = DataSource.loadWeaponImage(name, ctx)
    }
}