package com.cba.transactionaccount.util


import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import java.util.*

object DateTimeExtensions {
    val DATE_LOCALE: Locale = Locale.ENGLISH
    const val TIME_FORMAT = "h:mm a"
    const val DATE_FORMAT = "dd MMM yyyy"
    const val DATE_FORMAT_WITH_DAY = "EEE, dd MMM yyyy"
}

fun LocalDateTime.asDateString(): String = toString(DATE_FORMAT, DATE_LOCALE)

fun LocalDate.asDateString(): String = toString(DATE_FORMAT, DATE_LOCALE)

fun LocalDate.asDateStringWithDay(): String = toString(DATE_FORMAT_WITH_DAY, DATE_LOCALE)

fun LocalDateTime?.asTimeString(): String? = this?.toString(TIME_FORMAT, DATE_LOCALE)?.lowercase(Locale.getDefault())

fun LocalDate.ordinalDay() =
        when (dayOfMonth) {
            1, 21, 31 -> "${dayOfMonth}st"
            2, 22 -> "${dayOfMonth}nd"
            3, 23 -> "${dayOfMonth}rd"
            else -> "${dayOfMonth}th"
        }