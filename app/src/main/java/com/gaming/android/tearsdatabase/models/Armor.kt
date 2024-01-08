package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Armor(
    val actor_name: String,
    override val name: String,
    val set_name: String,
    val effect: List<String>,
    val set_bonus: List<String>,
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
    val upgrade_s1: List<String>,
    val upgrade_s2: List<String>,
    val upgrade_s3: List<String>,
    val upgrade_s4: List<String>,
    val total_upgrades: List<String>,
    val location: String,
    val coordinates: String
): Item<Armor> {
    @DrawableRes
    override var image: Int = R.drawable.mushroom_skewer
    override fun get(): Armor {
        return this
    }


    override fun setDrawable(@DrawableRes int: Int): Armor {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Armor {
        findDrawable(ctx)
        return this
    }

}