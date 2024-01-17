package com.gaming.android.tearsdatabase.models

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gaming.android.tearsdatabase.R
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.UUID

//todo we want to map meals to a list of recipes
@Entity
@JsonClass(generateAdapter = true)
data class Meal(
    val actor_name: String,
    @PrimaryKey
    override val name: String,
    val recipe_no: Int,
    val recipe: List<List<String>>,
    val bonus_heart: Int?,
    val bonus_level: Int?,
    val bonus_time: Int?,
    @DrawableRes
    override var image: Int = R.drawable.mushroom_skewer
        ): Item<Meal> {


    override fun get(): Meal {
        return this
    }

    override fun setDrawable(@DrawableRes int: Int): Meal {
        image = int
        return this
    }

    override fun setDrawable(ctx: Context): Meal {
        findDrawable(ctx)
        return this
    }
}