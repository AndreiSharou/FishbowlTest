package com.example.data.resource.local.room.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.data.resource.local.room.model.ContentLocalModel
import com.example.data.resource.local.room.model.NavigationLocalModel
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
    fun toNavigationLocalModel(value: String): List<NavigationLocalModel> {
        val type = object : TypeToken<List<NavigationLocalModel>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromNavigationLocalModel(list: List<NavigationLocalModel>): String {
        return gson.toJson(list)
    }
}