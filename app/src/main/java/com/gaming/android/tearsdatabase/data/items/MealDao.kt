package com.gaming.android.tearsdatabase.data.items

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gaming.android.tearsdatabase.models.Meal
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM meal")
    fun getMeals(): Flow<List<Meal>>

    @Query("SELECT * FROM meal WHERE LOWER(name)=LOWER(:name)")
    suspend fun getMeal(name: String): Meal

    @Query("SELECT * FROM meal WHERE LOWER(name) LIKE LOWER(:search) OR LOWER(recipe) LIKE LOWER(:search)")
    fun search(search: String): Flow<List<Meal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllMeals(meal: List<Meal>)

    @Query("DELETE FROM meal")
    suspend fun delete()
}