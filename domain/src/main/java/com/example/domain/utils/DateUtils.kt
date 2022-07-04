package com.example.domain.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private const val TIME_ZONE = "GMT"

    private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH).apply {
        timeZone = TimeZone.getTimeZone(TIME_ZONE)
    }
    private val formatterString = SimpleDateFormat("MMMM DD hh:mm a", Locale.ENGLISH).apply {
        timeZone = TimeZone.getTimeZone(TIME_ZONE)
    }

    fun getDateFromString(date: String?): Date {
        return date?.let { formatter.parse(it) } ?: Date()
    }


    fun getStringFromDate(date: Date): String {
        return formatterString.format(date)
    }
}