package com.gaming.android.tearsdatabase

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item (
    val name: String,
    val weapon: Weapon,
    @DrawableRes val imageResId: Int
        )