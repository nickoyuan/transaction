package com.cba.transactionaccount.ui

import androidx.lifecycle.viewModelScope
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.ui.TransactionViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TransactionAccountViewModel @Inject constructor() :
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
            is EventSuccess -> TransactionViewState.Successful(viewEvent.data)
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
            var taet = listOf(
                TransactionHistory(
                    true,
                    "adslfjals",
                    "shopping",
                    "231"
                ),
                TransactionHistory(
                    true,
                    "asfe",
                    "shopping",
                    "231"
                ),
                TransactionHistory(
                    true,
                    "fasgsab",
                    "shopping",
                    "231"
                ),
                TransactionHistory(
                    true,
                    "adslfjals",
                    "shopping",
                    "1124"
                )
            )
            withContext(SINGLE_THREAD) {
                emitEvent(EventSuccess(taet))
            }
        }
    }

    companion object {
        private val SINGLE_THREAD = newSingleThreadContext("transaction_thread")
    }

}

sealed class TransactionViewEvent {
    object EventLoading : TransactionViewEvent()
    object EventFetch : TransactionViewEvent()
    object EventClose : TransactionViewEvent()
    class EventError(val errorMessage: String) : TransactionViewEvent()
    class EventSuccess(val data: List<TransactionHistory>) : TransactionViewEvent()
}

sealed class TransactionViewState {
    object Empty : TransactionViewState()
    object Loading : TransactionViewState()
    object Fetch : TransactionViewState()
    class Successful(val data: List<TransactionHistory>) : TransactionViewState()
    class Error(val message: String) : TransactionViewState()
}