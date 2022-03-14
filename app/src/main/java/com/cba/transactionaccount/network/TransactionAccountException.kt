package com.cba.transactionaccount.network

sealed class TransactionAccountException : Throwable() {
    object TransactionServerDown: TransactionAccountException()
    object TransactionServerError : TransactionAccountException()
    object UnexpectedTransactionException : TransactionAccountException()
}