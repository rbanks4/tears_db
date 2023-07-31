package com.gaming.android.tearsdatabase.data

import android.content.Context
import android.util.Log
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.api.*
import com.gaming.android.tearsdatabase.models.*
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream

const val ARMOR_JSON = "armor.json"
const val BOWS_JSON = "bows.json"
const val MATERIALS_JSON = "materials.json"
const val RECIPES_JSON = "recipe.json"
const val ROASTED_JSON = "roasted.json"
const val SHIELDS_JSON = "shields.json"
const val WEAPONS_JSON = "weapons.json"
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

        fun readJsonBackup(context: Context, name: String): String? {
            val jsonString: String
            jsonString = try {
                val inputStream: InputStream = context.getAssets().open(name)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }

            Log.d("DataSource", jsonString)

            return jsonString
        }

//        fun armorBackup(context: Context): List<Armor> {
//            val response = Gson().fromJson(
//                readJsonBackup(context, ARMOR_JSON),
//                ArmorResponse::class.java
//            )
//
//            return response.documents
//        }
        fun bowsBackup(context: Context): List<Bow> {
            val response = Gson().fromJson(
                readJsonBackup(context, BOWS_JSON),
                BowsResponse::class.java
            )

            return response.documents
        }
        fun materialsBackup(context: Context): List<Material> {
            val response = Gson().fromJson(
                readJsonBackup(context, MATERIALS_JSON),
                MaterialsResponse::class.java
            )

            return response.documents
        }
        fun recipeBackup(context: Context): List<Meal> {
            val response = Gson().fromJson(
                readJsonBackup(context, RECIPES_JSON),
                MealsResponse::class.java
            )

            return response.documents
        }
        fun roastedBackup(context: Context): List<RoastedFood> {
            val response = Gson().fromJson(
                readJsonBackup(context, ROASTED_JSON),
                RoastedFoodResponse::class.java
            )

            return response.documents
        }
        fun shieldsBackup(context: Context): List<Shield> {
            val response = Gson().fromJson(
                readJsonBackup(context, SHIELDS_JSON),
                ShieldsResponse::class.java
            )

            return response.documents
        }
        fun weaponBackup(context: Context): List<Weapon> {
            val response = Gson().fromJson(
                readJsonBackup(context, WEAPONS_JSON),
                WeaponsResponse::class.java
            )

            return response.documents
        }
    }
}