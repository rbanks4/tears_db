package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.DataSource

data class Bow(
    val name: String,
    val compendium_no: Int,
    val base_attack: Int,
    val durability: Int,
    val range: Int,
    val drawing_time: Float,
    val reload_time: Float,
    val sub_type: String,
    val additional_damage: Int?,
    val sub_type2: String,
    val other: String
) {
    @DrawableRes
    var image: Int = R.drawable.wooden_stick


    @DrawableRes
    fun getDrawable(context: Context): Int {
        if(image == 0)
            image = DataSource.loadWeaponImage(name, context)
        return image
    }

    fun setDrawable(@DrawableRes int: Int): Bow {
        image = int
        return this
    }

    fun setDrawable(ctx: Context): Bow {
        findDrawable(ctx)
        return this
    }

    fun findDrawable(ctx: Context) {
        image = DataSource.loadWeaponImage(name, ctx)
    }

}