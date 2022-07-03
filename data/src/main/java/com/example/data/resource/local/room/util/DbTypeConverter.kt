package com.example.data.resource.local.room.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.data.resource.local.room.model.ContentLocalModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class DbTypeConverter @Inject constructor(private val gson: Gson) {

    @TypeConverter
    fun toContentLocalModel(value: String): ContentLocalModel {
        val type = object : TypeToken<ContentLocalModel>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromContentLocalModel(content: ContentLocalModel): String {
        return gson.toJson(content)
    }

    @TypeConverter
    fun toNavigation(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromNavigation(list: List<String>): String {
        return gson.toJson(list)
    }
}