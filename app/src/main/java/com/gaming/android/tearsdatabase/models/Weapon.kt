package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.R

data class Weapon (
    override val name: String,
    val compendium_no: Int,
    val base_attack: Int,
    val shown_attack: Int,
    val durability: Int,
    val guard_break_power: Int,
    val sub_type: String,
    val fuse_durability: Int?,
    val fuse_damage: Int,
    val sub_type2: String,
    val attach_zoani_attk: Int?,
    val shield_bash_damage: Int
        ): Item<Weapon> {
    @DrawableRes
    override var image: Int = R.drawable.wooden_stick

    override fun get(): Weapon {
        return this
    }

    override fun setDrawable(@DrawableRes int: Int): Weapon {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Weapon {
        findDrawable(ctx)
        return this
    }
}