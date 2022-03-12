package com.cba.transactionaccount.network

import com.cba.transactionaccount.model.TransactionData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionAccountRepo @Inject constructor(private val transactionAccountProvider: TransactionAccountProvider) {
    suspend fun getTransactionData(): TransactionData {
        return transactionAccountProvider.getTransaction("1")
    }
}