package com.cba.transactionaccount.model

import com.google.gson.annotations.SerializedName
import org.joda.time.LocalDate
import java.io.Serializable

data class TransactionHistory(
    @SerializedName("amount")
    val amount : String,
    @SerializedName("isPending")
    val isPending: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("effectiveDate")
    val effectiveDate: LocalDate
) : Serializable