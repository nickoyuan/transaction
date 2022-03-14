package com.cba.transactionaccount.util

import com.cba.transactionaccount.model.AccountData
import com.cba.transactionaccount.model.TransactionData
import com.cba.transactionaccount.model.TransactionHistory
import org.joda.time.LocalDate

class TestConstants {

    companion object {
        val mockAccount = AccountData(
            bsb = "123124",
            accountNumber = "123123",
            balance = "12",
            available = "123123",
            accountName = "12"
        )

        val mockTransaction = TransactionHistory(
            amount = "123",
            true,
            "23123",
            "shopping",
            LocalDate.parse("2019-10-10"),
        )

        val mockTransactionData = TransactionData(
            mockAccount,
            transactions = listOf(mockTransaction)
        )

    }

}