package com.gaming.android.tearsdatabase.data

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.api.*
import com.gaming.android.tearsdatabase.models.*
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

const val ARMOR_JSON = "armor.json"
const val BOWS_JSON = "bows.json"
const val MATERIALS_JSON = "materials.json"
const val RECIPES_JSON = "recipe.json"
const val ROASTED_JSON = "roasted.json"
const val SHIELDS_JSON = "shields.json"
const val WEAPONS_JSON = "weapons.json"
const val EFFECTS_JSON = "effects.json"
const val DRAWABLE = "drawable"
data class DataSource(private val ctx: Context): ContextWrapper(ctx) {

    companion object {

        @DrawableRes
        fun loadWeaponImage(name: String, context: Context): Int {
            return getDrawable(context, camelToSnakeCase(name))
        }

        private fun getDrawable(context: Context, name: String): Int {
            return context.resources.getIdentifier(name, DRAWABLE, context.packageName)
        }

        fun splitEffectNames(name: String): List<String> {
            val names = name.split("\n")
            return names
        }

        fun getEffectsByName(map: Map<String, Effect>?, name: String): List<Effect> {
            val names = splitEffectNames(name)
            return names.mapNotNull {
                map?.get(it)
            }
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
    }

    fun armorBackup(): List<Armor> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, ARMOR_JSON),
            ArmorResponse::class.java
        )

        return response.documents
    }
    fun bowsBackup(): List<Bow> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, BOWS_JSON),
            BowsResponse::class.java
        )

        return response.documents
    }
    fun materialsBackup(): List<Material> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, MATERIALS_JSON),
            MaterialsResponse::class.java
        )

        return response.documents
    }
    fun recipeBackup(): List<Meal> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, RECIPES_JSON),
            MealsResponse::class.java
        )

        return response.documents
    }
    fun roastedBackup(): List<RoastedFood> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, ROASTED_JSON),
            RoastedFoodResponse::class.java
        )

        return response.documents
    }
    fun shieldsBackup(): List<Shield> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, SHIELDS_JSON),
            ShieldsResponse::class.java
        )

        return response.documents
    }
    fun weaponBackup(): List<Weapon> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, WEAPONS_JSON),
            WeaponsResponse::class.java
        )

        return response.documents
    }

    fun effectsBackup(): List<Effect> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, EFFECTS_JSON),
            EffectResponse::class.java
        )

        return response.documents
    }
}