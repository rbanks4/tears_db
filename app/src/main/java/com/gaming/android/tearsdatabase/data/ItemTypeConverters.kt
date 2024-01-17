package com.gaming.android.tearsdatabase.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ItemTypeConverters {
    @TypeConverter
    fun fromString(value: String?): List<List<String>> {
        val listType: Type = object : TypeToken<ArrayList<ArrayList<String?>?>?>() {}.type
        return Gson().fromJson<ArrayList<ArrayList<String>>>(value, listType)
    }

    @TypeConverter
    fun fromListListString(list: List<List<String>>): String {
        return Gson().toJson(list)
    }
}