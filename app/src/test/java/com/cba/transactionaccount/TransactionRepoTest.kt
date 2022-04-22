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




/*

import org.bouncycastle.crypto.CryptoException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import retrofit2.HttpException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever


@Test
fun shouldReturnExceptionWhenClientValuesHasNotBeenGenerated() {
    val exception = AuthenticationClientException("AuthenticationClientException")
    whenever(authenticationClient.generateClientValues(PIN, CHALLENGE_RESPONSE)).thenThrow(exception)

    loanOfferService.acceptLoanOffer(PIN, LOAN_OFFER_DATA).testSubscribeError(exception)

    verify(authenticationClient).generateClientValues(PIN, CHALLENGE_RESPONSE)
    verify(loanRepo, never()).acceptLoanOffer(any())
    verify(accountRepo, never()).clearAccounts()
}

Single extensions in the Repository


import io.reactivex.Observable
import io.reactivex.Single
import org.mockito.stubbing.OngoingStubbing

fun <T> OngoingStubbing<Single<T>>.thenSuccess(value: T): OngoingStubbing<Single<T>> = thenReturn(Single.just(value))

fun <T> OngoingStubbing<Observable<T>>.thenSuccessObservable(value: T): OngoingStubbing<Observable<T>> = thenReturn(Observable.just(value))

fun <T> OngoingStubbing<Single<T>>.thenError(throwable: Throwable): OngoingStubbing<Single<T>> = thenReturn(Single.error(throwable))

fun <T> OngoingStubbing<Observable<T>>.thenErrorObservable(throwable: Throwable): OngoingStubbing<Observable<T>> = thenReturn(Observable.error(throwable))



REPO TEST
 whenever(authenticationRepo.verifyPin(verifyPinRequest)).thenError(createHttpException(401))

    val throwable = authenticationService.verifyPin(EXISTING_PIN).testSubscribeError(HttpException::class)

    assertTrue(hasStatusCode(401, throwable))



fun createHttpException(statusCode: Int, messageBody: String = "") =
    HttpException(error<Any>(statusCode, messageBody.toResponseBody(CONTENT_TYPE_JSON.toMediaType())))




fun <E : Throwable> Completable.testSubscribeError(expceptionType: KClass<E>? = null): Throwable = with(test()) {
awaitTerminalEvent()
expceptionType?.let { assertError(it.java) }
errors()[0]
}


fun hasStatusCode(statusCode: Int, ex: Throwable) = ex is HttpException && ex.code() == statusCode


 */
