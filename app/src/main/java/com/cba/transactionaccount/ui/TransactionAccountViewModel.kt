package com.cba.transactionaccount.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.model.TransactionViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class TransactionAccountViewModel @Inject constructor() : ViewModel() {

    val screenState: LiveData<TransactionViewState<List<TransactionHistory>>> get() = _screenState
    private val _screenState = MutableLiveData<TransactionViewState<List<TransactionHistory>>>(TransactionViewState.idle())

    fun loadTransactions() {
        viewModelScope.launch {
            var taet = listOf(TransactionHistory(
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
                _screenState.postValue(TransactionViewState.success(taet))
            }
        }
    }

    companion object {
        private val SINGLE_THREAD = newSingleThreadContext("transaction_thread")
    }

}

data class PostTransactionListState(
    val transactionList: List<TransactionHistory> = emptyList()
)