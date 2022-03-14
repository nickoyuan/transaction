package com.cba.transactionaccount.util

import android.text.Spanned
import androidx.core.text.HtmlCompat
import com.cba.transactionaccount.util.DateTimeExtensions.DATE_FORMAT_WITH_DAY
import com.cba.transactionaccount.util.DateTimeExtensions.DATE_LOCALE
import org.joda.time.LocalDate
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

object DateTimeExtensions {
    val DATE_LOCALE: Locale = Locale.ENGLISH
    const val DATE_FORMAT_WITH_DAY = "EE dd MMM"
}

fun LocalDate.dateToString() : String = toString(DATE_FORMAT_WITH_DAY, DATE_LOCALE)

fun String.toCurrencyString() : String {
   return NumberFormat.getCurrencyInstance().format(BigDecimal(this))
}

fun String.toHtml() : Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}