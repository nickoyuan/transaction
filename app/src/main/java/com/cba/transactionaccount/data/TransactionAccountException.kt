package com.cba.transactionaccount.data

sealed class TransactionAccountException : Throwable() {
    object TransactionServerDown: TransactionAccountException()
    object TransactionServerError : TransactionAccountException()
    object UnexpectedTransactionException : TransactionAccountException()
}