package com.cba.transactionaccount.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.cba.transactionaccount.domain.GetTransactionListWithSortedDateUseCase
import com.cba.transactionaccount.model.AdapterData
import com.cba.transactionaccount.ui.TransactionViewEvent.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@ExperimentalPagingApi
class TransactionAccountViewModel
@Inject constructor(
    private val getTransactionListWithSortedDateUseCase: GetTransactionListWithSortedDateUseCase
) :
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
            emitEvent(EventLoading)
            try {
                getTransactionListWithSortedDateUseCase().cachedIn(this).collect {
                    emitEvent(EventSuccess(it))
                }
            } catch (e: Exception) {
                emitEvent(EventError(e.message))
            }
        }
    }
}

sealed class TransactionViewEvent {
    object EventLoading : TransactionViewEvent()
    object EventFetch : TransactionViewEvent()
    class EventError(val errorMessage: String?) : TransactionViewEvent()
    class EventSuccess(val data: PagingData<AdapterData>) : TransactionViewEvent()
}

sealed class TransactionViewState {
    object Empty : TransactionViewState()
    object Loading : TransactionViewState()
    object Fetch : TransactionViewState()
    class Successful(val data: PagingData<AdapterData>) : TransactionViewState()
    class Error(val message: String) : TransactionViewState()
}