package com.cba.transactionaccount.model

import org.joda.time.LocalDate

sealed class AdapterData {
    data class data(val data: TransactionHistory) : AdapterData()
    data class SeparatorItem(val localDate: LocalDate) : AdapterData()
}