package com.gaming.android.tearsdatabase.models

import android.content.Context
import android.util.Log
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.data.DataSource
import com.gaming.android.tearsdatabase.R

data class Weapon (
    val name: String,
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
        ) {
    @DrawableRes var image: Int = R.drawable.wooden_stick


    @DrawableRes
    fun getDrawable(context: Context): Int {
        if(image == 0)
            image = DataSource.loadWeaponImage(name, context)
        return image
    }

    fun setDrawable(@DrawableRes int: Int): Weapon {
        image = int
        return this
    }

    fun setDrawable(ctx: Context): Weapon {
        findDrawable(ctx)
        return this
    }

    fun findDrawable(ctx: Context) {
        image = DataSource.loadWeaponImage(name, ctx)
    }

    fun toParcelable(): WeaponParcel {
        return WeaponParcel(name, compendium_no, base_attack, shown_attack, durability, guard_break_power, sub_type, fuse_durability?:0, fuse_damage, sub_type2, attach_zoani_attk?:0, shield_bash_damage)
    }
}