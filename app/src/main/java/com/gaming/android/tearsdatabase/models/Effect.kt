package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Effect(
    override val name: String,
    val effect_name: String?,
    val level: Int?,
    val level_highest: Int?,
    val value: String,
    val required_potency: Int?,
    val highst_potency: Int?,
    val base_time: Int?,
    val monochrome: Boolean
) : Item<Effect> {
    @DrawableRes
    override var image: Int = R.drawable.lifemaxup

    var effect_level1: Effect? = null
    var effect_level2: Effect? = null
    var effect_level3: Effect? = null

    override fun get(): Effect {
        return this
    }

    override fun setDrawable(@DrawableRes int: Int): Effect {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Effect {
        findDrawable(ctx)
        return this
    }
}