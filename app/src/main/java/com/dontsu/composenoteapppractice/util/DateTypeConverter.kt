package com.dontsu.composenoteapppractice.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.*

@ProvidedTypeConverter
object DateTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

}
