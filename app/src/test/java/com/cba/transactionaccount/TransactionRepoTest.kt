package com.cba.transactionaccount

import com.cba.transactionaccount.data.TransactionAccountProvider
import com.cba.transactionaccount.data.TransactionAccountRepo
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

@ExperimentalCoroutinesApi
class TransactionRepoTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    val transactionProvider =  mock<TransactionAccountProvider>()

    lateinit var transactionRepo: TransactionAccountRepo

    @Before
    fun setup() {
//        transactionRepo = TransactionAccountRepo(transactionProvider)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should get transaction history when calling get endpoint`() {
//        runTest {
//            val dl = "1"
//            whenever(transactionProvider.getTransaction(dl)).thenReturn(mockTransactionData)
//
//            val data = transactionRepo.getTransactionData()
//
//            assertEquals(data.transactions, mockTransactionData.transactions)
//            assertEquals(data.account, mockTransactionData.account)
//
//            verify(transactionProvider).getTransaction(dl)
//        }
    }
}