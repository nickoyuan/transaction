package com.cba.transactionaccount.database

import androidx.room.TypeConverter
import org.joda.time.LocalDate

class DateTimeConverters {
    @TypeConverter
    fun fromTimestamp(value: String?): LocalDate? {
        return value?.let {
            LocalDate.parse(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): String? {
        return date?.toString()
    }
}