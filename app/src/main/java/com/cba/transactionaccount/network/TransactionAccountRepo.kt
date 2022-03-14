package com.cba.transactionaccount.network

import com.cba.transactionaccount.model.TransactionData
import com.cba.transactionaccount.network.TransactionAccountException.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.HttpURLConnection.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionAccountRepo @Inject constructor(private val transactionAccountProvider: TransactionAccountProvider) {
    suspend fun getTransactionData(): TransactionData =
        try {
            withContext(Dispatchers.IO) {
                transactionAccountProvider.getTransaction("1")
            }
        } catch (e : Exception) {
            when(e) {
                is HttpException ->
                    when(e.code()) {
                        HTTP_UNAVAILABLE -> throw TransactionServerDown
                        HTTP_INTERNAL_ERROR -> throw TransactionServerError
                        else -> throw UnexpectedTransactionException
                    }
                else -> throw UnexpectedTransactionException
            }
        }

}