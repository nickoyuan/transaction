package com.cba.transactionaccount.model
import com.cba.transactionaccount.model.TransactionViewState.State.*

data class TransactionViewState<T>(val state: State, val data: T?) {

    enum class State {
        SUCCESS,
        LOADING,
        ERROR,
        IDLE
    }

    companion object {
        fun <T> success(data: T): TransactionViewState<T> {
            return TransactionViewState(SUCCESS, data)
        }

        fun <T> loading(): TransactionViewState<T> {
            return TransactionViewState(LOADING, null)
        }

        fun <T> error(): TransactionViewState<T> {
            return TransactionViewState(ERROR, null)
        }

        fun <T> idle(): TransactionViewState<T> {
            return TransactionViewState(IDLE, null)
        }
    }
}