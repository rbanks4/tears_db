package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R
import com.gaming.android.tearsdatabase.data.DataSource

data class Shield(
    override val name: String,
    val compendium_no: Int,
    val guard_power: Int,
    val durability: Int,
    val shield_surfing_damage_ratio: Float,
    val shield_surfing_friction: Float,
    val sub_type: String,
    val additional_damage: Int?,
    val sub_type2: String
): Item<Shield> {
    @DrawableRes
    override var image: Int = R.drawable.travelers_shield


    override fun setDrawable(@DrawableRes int: Int): Shield {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Shield {
        findDrawable(ctx)
        return this
    }
}