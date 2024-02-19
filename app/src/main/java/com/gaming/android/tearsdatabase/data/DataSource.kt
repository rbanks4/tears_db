package com.gaming.android.tearsdatabase.data

import android.content.Context
import android.content.ContextWrapper
import android.util.Log
import androidx.annotation.DrawableRes
import com.gaming.android.tearsdatabase.api.response.ArmorResponse
import com.gaming.android.tearsdatabase.api.response.BowsResponse
import com.gaming.android.tearsdatabase.api.response.EffectResponse
import com.gaming.android.tearsdatabase.api.response.MaterialsResponse
import com.gaming.android.tearsdatabase.api.response.MealsResponse
import com.gaming.android.tearsdatabase.api.response.RoastedFoodResponse
import com.gaming.android.tearsdatabase.api.response.ShieldsResponse
import com.gaming.android.tearsdatabase.api.response.WeaponsResponse
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

        fun getEffectsByName(map: Map<String, Effect>?, names: List<String>): List<Effect> {
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

        fun recipeFormat(recipe: List<List<String>>): List<String> {
            var recipeList = mutableListOf<String>()
            for(ingredients in recipe.indices){
                var countLabel = ingredients + 1
                for(option in recipe[ingredients].indices){
                    var optString = recipe[ingredients][option]
                    if(option != recipe[ingredients].lastIndex)
                        optString = "$optString, or "
                    if(recipeList.lastIndex != ingredients) {
                        recipeList.add(
                            ingredients, "Cook Slot $countLabel: $optString"
                        )
                    } else {
                        recipeList[ingredients] += optString
                    }
                }
            }
            return recipeList
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

            Log.d("DataSource", "Size of $name json: ${jsonString.length}")

            return jsonString
        }
    }

    fun armorBackup(): List<Armor> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, ARMOR_JSON),
            ArmorResponse::class.java
        )

        return response.armor
    }
    fun bowsBackup(): List<Bow> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, BOWS_JSON),
            BowsResponse::class.java
        )

        return response.bows
    }
    fun materialsBackup(): List<Material> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, MATERIALS_JSON),
            MaterialsResponse::class.java
        )

        return response.materials
    }
    fun recipeBackup(): List<Meal> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, RECIPES_JSON),
            MealsResponse::class.java
        )

        return response.meals
    }
    fun roastedBackup(): List<RoastedFood> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, ROASTED_JSON),
            RoastedFoodResponse::class.java
        )

        return response.roasted
    }
    fun shieldsBackup(): List<Shield> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, SHIELDS_JSON),
            ShieldsResponse::class.java
        )

        return response.shields
    }
    fun weaponBackup(): List<Weapon> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, WEAPONS_JSON),
            WeaponsResponse::class.java
        )

        return response.weapons
    }

    fun effectsBackup(): List<Effect> {
        val response = Gson().fromJson(
            readJsonBackup(ctx, EFFECTS_JSON),
            EffectResponse::class.java
        )

        return response.effects
    }
}