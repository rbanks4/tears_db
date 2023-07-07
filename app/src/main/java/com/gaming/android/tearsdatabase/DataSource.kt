package com.gaming.android.tearsdatabase

import android.content.Context
import androidx.annotation.DrawableRes

class DataSource {

    companion object {
        fun loadWeaponImages(weapons: List<Weapon>, context: Context): List<Item> {
            return weapons.map { Item(it.name, it, it.getDrawable(context)?:R.drawable.wooden_stick) }
        }

        @DrawableRes
        fun loadWeaponImage(name: String, context: Context): Int {
            return getDrawable(context, camelToSnakeCase(name))
        }

        private fun getDrawable(context: Context, name: String): Int {
            return context.resources.getIdentifier(name, "drawable", context.packageName)
        }

        private fun camelToSnakeCase(name: String): String {
            val pattern = " .".toRegex()
            return name.replace("\\(|\\)".toRegex(), "")
                .replace("'", "")
                .replace(pattern, "_$0")
                .replace(" ", "")
                .replace("-", "_")
                .lowercase()
        }
    }
}