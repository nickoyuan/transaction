package com.cba.transactionaccount

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.data.TransactionAccountRepo
import com.cba.transactionaccount.ui.TransactionAccountViewModel
import com.cba.transactionaccount.ui.TransactionViewState
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.joda.time.LocalDate
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class TransactionViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    val transactionRepository: TransactionAccountRepo = mock()
    val viewModelObserver: Observer<TransactionViewState> = mock()

    lateinit var viewModel: TransactionAccountViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = TransactionAccountViewModel(transactionRepository)
        viewModel.uiState.observeForever(viewModelObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        viewModel.uiState.removeObserver(viewModelObserver)
    }

    @Test
    fun `testing this`() {

    }



    fun filteredTransaction(list : List<TransactionHistory>): Map<LocalDate, List<TransactionHistory>> {
        return list.sortedBy {
                it.effectiveDate
            }.groupBy {
                it.effectiveDate
            }.mapValues {
                it.value.sortedWith(
                    compareBy { it.isPending == false }
                )
            }
    }

//    @Test
//    fun `should return transaction data when fetching transaction history`() {
//        runTest {
////            whenever(transactionRepository.getTransactionData()).thenReturn(mockTransactionData)
//
//            async {
//                viewModel.emitEvent(TransactionViewEvent.EventFetch)
//            }.await()
//
//            val argumentCaptor = ArgumentCaptor.forClass(TransactionViewState::class.java)
//
//            verify(transactionRepository).getTransactionData()
//
//            argumentCaptor.run {
//                verify(viewModelObserver, times(4)).onChanged(capture())
//                val (emptyState, fetch, loading, successful) = allValues
//                assertEquals(emptyState, TransactionViewState.Empty)
//                assertEquals(fetch, TransactionViewState.Fetch)
//                assertEquals(loading, TransactionViewState.Loading)
//
//                val success = successful as TransactionViewState.Successful
//                assertEquals(success.data, mockTransactionData.transactions)
//                assertEquals(success.account, mockTransactionData.account)
//                assertEquals(
//                    success::class, TransactionViewState.Successful::class
//                )
//            }
//
//        }
//    }
//
//
//    companion object {
//        val mockAccount = AccountData(
//            bsb = "123124",
//            accountNumber = "123123",
//            balance = "12",
//            available = "123123",
//            accountName = "12"
//        )
//
////        val mockTransaction = TransactionHistory(
////            amount = "123",
////            true,
////            "23123",
////            "shopping",
////            LocalDate.parse("2019-10-10"),
////        )
//
//        val mockTransactionData = TransactionData(
//            mockAccount,
//            transactions = listOf(mockTransaction)
//        )
//
//    }

}