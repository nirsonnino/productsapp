package com.nino.productsapp.util

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromStringArrayList(value: List<String>): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<String> {
        return try {
            Gson().fromJson<List<String>>(value)
        } catch (e: Exception) {
            arrayListOf()
        }
    }

}