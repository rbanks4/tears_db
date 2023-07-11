package com.gaming.android.tearsdatabase.models

import androidx.annotation.DrawableRes

data class Item (
    val name: String,
    val weapon: Weapon,
    @DrawableRes val imageResId: Int
        )