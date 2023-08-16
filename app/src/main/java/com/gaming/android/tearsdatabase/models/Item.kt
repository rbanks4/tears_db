package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.data.DataSource

interface Item<T> {
    val name: String

    @get:DrawableRes
    var image: Int

    fun get(): T

    fun setDrawable(@DrawableRes int: Int): T

    fun setDrawable(ctx: Context): T

    fun findDrawable(ctx: Context) {
        image = DataSource.loadWeaponImage(name, ctx)
    }
}