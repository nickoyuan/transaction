package com.cba.transactionaccount.data

import com.cba.transactionaccount.model.TransactionData
import retrofit2.http.GET
import retrofit2.http.Query

interface TransactionAccountProvider {
    @GET("s/inyr8o29shntk9w/exercise.json")
    suspend fun getTransaction(@Query("dl") dl : Int) : TransactionData
}