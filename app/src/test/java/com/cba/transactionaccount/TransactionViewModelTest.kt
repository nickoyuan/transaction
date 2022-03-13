package com.cba.transactionaccount

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.cba.transactionaccount.model.AccountData
import com.cba.transactionaccount.model.TransactionData
import com.cba.transactionaccount.model.TransactionHistory
import com.cba.transactionaccount.network.TransactionAccountRepo
import com.cba.transactionaccount.ui.TransactionAccountViewModel
import com.cba.transactionaccount.ui.TransactionViewEvent
import com.cba.transactionaccount.ui.TransactionViewState
import com.cba.transactionaccount.util.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.joda.time.LocalDate
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TransactionViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var transactionRepository: TransactionAccountRepo

    lateinit var viewModel: TransactionAccountViewModel

    @Mock
    private lateinit var viewModelObserver: Observer<TransactionViewState>

    @Before
    fun setUp() {
        viewModel = TransactionAccountViewModel(transactionRepository)
        viewModel.uiState.observeForever(viewModelObserver)
    }

    @After
    fun tearDown() {
        viewModel.uiState.removeObserver(viewModelObserver)
    }

    @Test
    fun `should return transaction data when fetching transaction history`() {
        testCoroutineRule.runBlockingTest {
            `when`(transactionRepository.getTransactionData()).thenReturn(mockTransactionData)

            viewModel.emitEvent(TransactionViewEvent.EventFetch)

            verify(transactionRepository).getTransactionData()

            val argumentCaptor = ArgumentCaptor.forClass(TransactionViewState::class.java)

            argumentCaptor.run {
                verify(viewModelObserver, times(4)).onChanged(capture())
                val (emptyState, fetch, loading, successful) = allValues
                assertEquals(emptyState, TransactionViewState.Empty)
                assertEquals(fetch, TransactionViewState.Fetch)
                assertEquals(loading, TransactionViewState.Loading)

                val success = successful as TransactionViewState.Successful
                assertEquals(success.data, mockTransactionData.transactions)
                assertEquals(success.account, mockTransactionData.account)
                assertEquals(
                    success::class, TransactionViewState.Successful::class
                )
            }

        }
    }


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