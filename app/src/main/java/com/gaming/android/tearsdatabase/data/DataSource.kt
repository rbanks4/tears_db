package com.gaming.android.tearsdatabase.data

import android.content.Context
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.models.Item
import com.gaming.android.tearsdatabase.models.Weapon

class DataSource {

    companion object {
        fun loadWeaponImages(weapons: List<Weapon>, context: Context): List<Item> {
            return weapons.map { Item(it.name, it, it.getDrawable(context)) }
        }

        @DrawableRes
        fun loadWeaponImage(name: String, context: Context): Int {
            return getDrawable(context, camelToSnakeCase(name))
        }

        private fun getDrawable(context: Context, name: String): Int {
            return context.resources.getIdentifier(name, "drawable", context.packageName)
        }

        fun camelToSnakeCase(name: String): String {
            val pattern = " .".toRegex()
            return name.replace("\\(|\\)".toRegex(), "")
                .replace("'", "")
                .replace(pattern, "_$0")
                .replace(" ", "")
                .replace("-", "_")
                .replace("é", "e")
                .replace("è", "e")
                .lowercase()
        }

        fun recipeFormat(recipe: String): String {
            return recipe.replace(" or", " or\n")
                .replace(" and", " and\n")
        }
    }
}