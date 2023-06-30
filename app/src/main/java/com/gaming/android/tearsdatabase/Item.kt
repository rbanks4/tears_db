package com.gaming.android.tearsdatabase

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item (
    @StringRes val stringResId: Int,
    @DrawableRes val imageResId: Int
        )