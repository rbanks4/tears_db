package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Material(
    override val name: String,
    val additional_damage: Int?,
    val selling_price: Int?,
    val buying_price: String?,
    val dye_color: String,
    val effect_type: String,
    val effect_level: Int?,
    val effect_time: Int?,
    val hp_recover: Int?,
    val sub_type: String,
    val sheild_bash_damage: Int?,
    val additional_damage_rate_arrow: Int?,
    val boost_effective_time: Int?,
    val boost_hp_recover: Int?,
    val boost_max_heart: Int?,
    val boost_max_stamina: Int?,
    val boost_critical_cook: Int?
    ): Item<Material> {
    @DrawableRes
    override var image: Int = R.drawable.hot_footed_frog

    override fun get(): Material {
        return this
    }

    override fun setDrawable(@DrawableRes int: Int): Material {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Material {
        findDrawable(ctx)
        return this
    }
}