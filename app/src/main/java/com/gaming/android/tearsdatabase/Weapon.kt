package com.gaming.android.tearsdatabase

import android.content.Context
import androidx.annotation.DrawableRes

data class Weapon (
    val name: String,
    val shown_attack: Int,
    val durability: Int,
    val sub_type: List<String>
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
        return WeaponParcel(name, shown_attack, durability, sub_type)
    }
}