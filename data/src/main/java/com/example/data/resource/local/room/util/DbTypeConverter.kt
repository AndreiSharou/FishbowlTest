package com.example.data.resource.local.room.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class DbTypeConverter @Inject constructor(private val gson: Gson) {

//    @TypeConverter
//    fun toSessionTradingPivot(value: String): List<SymbolListQuery.SessionTradingPivot> {
//        val listType = object : TypeToken<List<SymbolListQuery.SessionTradingPivot>>() {}.type
//        return gson.fromJson(value, listType)
//    }
//
//    @TypeConverter
//    fun fromSessionTradingPivot(list: List<SymbolListQuery.SessionTradingPivot>): String {
//        return gson.toJson(list)
//    }
}