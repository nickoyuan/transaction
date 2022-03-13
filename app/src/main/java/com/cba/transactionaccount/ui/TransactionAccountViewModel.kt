package com.cba.transactionaccount.ui

import androidx.lifecycle.viewModelScope
import com.cba.transactionaccount.model.AccountData
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.network.TransactionAccountRepo
import com.cba.transactionaccount.ui.TransactionViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TransactionAccountViewModel @Inject constructor(private val transactionAccountRepo: TransactionAccountRepo) :
    ViewModelUDF<TransactionViewState, TransactionViewEvent>() {

    override val initialState: TransactionViewState
        get() = TransactionViewState.Empty

    override fun handleStateUpdate(
        viewEvent: TransactionViewEvent,
        oldState: TransactionViewState
    ): TransactionViewState {
        return when (viewEvent) {
            is EventLoading -> TransactionViewState.Loading
            is EventFetch -> TransactionViewState.Fetch
            is EventSuccess -> TransactionViewState.Successful(viewEvent.data, viewEvent.account)
            else -> oldState
        }
    }

    override fun handleSideEffects(
        viewEvent: TransactionViewEvent,
        oldState: TransactionViewState,
        newState: TransactionViewState
    ) {
        when (viewEvent) {
            is EventFetch -> loadTransactions()
            else -> {}
        }
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                emitEvent(EventLoading)
                try {
                    val transaction = transactionAccountRepo.getTransactionData()
                    emitEvent(EventSuccess(transaction.transactions, transaction.account))
                } catch (e: Exception) {
                    emitEvent(EventError(e.message))
                }
            }
        }
    }
}

sealed class TransactionViewEvent {
    object EventLoading : TransactionViewEvent()
    object EventFetch : TransactionViewEvent()
    class EventError(val errorMessage: String?) : TransactionViewEvent()
    class EventSuccess(val data: List<TransactionHistory>, val account : AccountData) : TransactionViewEvent()
}

sealed class TransactionViewState {
    object Empty : TransactionViewState()
    object Loading : TransactionViewState()
    object Fetch : TransactionViewState()
    class Successful(val data: List<TransactionHistory>, val account : AccountData) : TransactionViewState()
    class Error(val message: String) : TransactionViewState()
}