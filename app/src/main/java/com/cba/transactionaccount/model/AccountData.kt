package com.cba.transactionaccount.model

import com.google.gson.annotations.SerializedName

data class AccountData(
    @SerializedName("bsb")
    var bsb: String,
    @SerializedName("accountNumber")
    var accountNumber: String,
    @SerializedName("balance")
    var balance: String,
    @SerializedName("available")
    var available: String,
    @SerializedName("accountName")
    var accountName: String
)