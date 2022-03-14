package com.cba.transactionaccount.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TransactionData(
    @SerializedName("account")
    val account : AccountData,
    @SerializedName("transactions")
    val transactions : List<TransactionHistory>
) : Serializable