package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Bow(
    val _id: Int,
    override val name: String,
    val compendium_no: Int,
    val base_attack: Int,
    val durability: Int,
    val range: Int,
    val drawing_time: Float,
    val reload_time: Float,
    override val sub_type: List<String>,
    val additional_damage: Int?,
    override val sub_type2: List<String>,
    val other: String
): Item<Bow>, BattleItem<Bow> {
    @DrawableRes
    override var image: Int = R.drawable.wooden_stick

    override fun get(): Bow {
        return this
    }

    override fun setDrawable(@DrawableRes int: Int): Bow {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Bow {
        findDrawable(ctx)
        return this
    }
}