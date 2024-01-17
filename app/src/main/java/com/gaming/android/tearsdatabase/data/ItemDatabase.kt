package com.gaming.android.tearsdatabase.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gaming.android.tearsdatabase.data.items.MealDao
import com.gaming.android.tearsdatabase.models.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(ItemTypeConverters::class)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}