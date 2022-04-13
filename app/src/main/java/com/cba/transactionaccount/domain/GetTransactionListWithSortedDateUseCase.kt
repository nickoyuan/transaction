package com.cba.transactionaccount.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.cba.transactionaccount.model.AdapterData
import com.cba.transactionaccount.data.TransactionAccountRepo
import com.cba.transactionaccount.di.IoDispatcher
import com.cba.transactionaccount.model.TransactionHistory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


@ExperimentalPagingApi
class GetTransactionListWithSortedDateUseCase @Inject constructor(
    private val transactionAccountRepo: TransactionAccountRepo,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    /**
     * Since our Usecase does just one thing, we can make our UseCase class callable
     * as if it was a normal function
     *
     * Thus we use operator
     * https://kotlinlang.org/docs/operator-overloading.html#invoke-operator
     */
     suspend operator fun invoke(): Flow<PagingData<AdapterData>> {
        return withContext(defaultDispatcher) {
            val transactionHistory = transactionAccountRepo.getTransactionData().map { pagingData ->
                pagingData.map { itemData ->
                    AdapterData.data(TransactionHistory(
                        amount = itemData.amount,
                        isPending = itemData.isPending,
                        description = itemData.description,
                        category = itemData.category,
                        effectiveDate = itemData.effectiveDate,
                        id = itemData.id
                    ))
                }.insertSeparators { before: AdapterData.data?, after: AdapterData.data? ->
                    if (after == null) {
                        // We're at end of the list
                        null
                    } else if (before == null || before.data.effectiveDate.dayOfMonth != after.data.effectiveDate.dayOfMonth) {
                        AdapterData.SeparatorItem(after.data.effectiveDate)
                    } else {
                        null
                    }
                }
            }
            transactionHistory
        }
    }

}