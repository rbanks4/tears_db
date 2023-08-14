package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Bow(
    override val name: String,
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
): Item<Bow> {
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