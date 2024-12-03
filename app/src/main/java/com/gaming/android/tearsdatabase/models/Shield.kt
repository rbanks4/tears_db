package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Shield(
    val _id: Int,
    override val name: String,
    val compendium: Int,
    val guard_power: Int,
    val durability: Int,
    val shield_surfing_damage_ratio: Float,
    val shield_surfing_friction: Float,
    override val sub_type: List<String>,
    val additional_damage: Int?,
    override val sub_type2: List<String>
): Item<Shield>, BattleItem<Shield> {
    @DrawableRes
    override var image: Int = R.drawable.travelers_shield

    override fun get(): Shield {
        return this
    }


    override fun setDrawable(@DrawableRes int: Int): Shield {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Shield {
        findDrawable(ctx)
        return this
    }
}