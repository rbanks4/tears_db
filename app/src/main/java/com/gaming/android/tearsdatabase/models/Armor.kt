package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.DataSource

data class Armor(
    val actor_name: String,
    val name: String,
    val set_name: String,
    val effect: String,
    val set_bonus: String,
    val buying_price: Int,
    val creating_price: Int,
    val base_defense: Int,
    val star_one: Int?,
    val star_two: Int?,
    val star_three: Int?,
    val star_four: Int?,
    val selling_price: Int,
    val selling_price_s1: Int?,
    val selling_price_s2: Int?,
    val selling_price_s3: Int?,
    val selling_price_s4: Int?,
    val upgrade_s1: String,
    val upgrade_s2: String,
    val upgrade_s3: String,
    val upgrade_s4: String,
    val total_upgrades: String,
    val location: String,
    val coordinates: String
) {
    @DrawableRes
    var image: Int = R.drawable.mushroom_skewer


    fun setDrawable(@DrawableRes int: Int): Armor {
        image = int
        return this
    }

    fun setDrawable(ctx: Context): Armor {
        findDrawable(ctx)
        return this
    }

    fun findDrawable(ctx: Context) {
        image = DataSource.loadWeaponImage(name, ctx)
    }
}