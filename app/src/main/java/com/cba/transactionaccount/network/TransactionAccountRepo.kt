package com.cba.transactionaccount.network

import androidx.paging.*
import com.cba.transactionaccount.model.AdapterData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionAccountRepo @Inject constructor(private val transactionRemoteMediator: TransactionRemoteMediator) {
    fun getTransactionData(): Flow<PagingData<AdapterData>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            initialKey = 1,
            pagingSourceFactory = {
                transactionRemoteMediator
            }
        ).flow
            .map { pagingData -> pagingData.map { itemData ->
               AdapterData.data(itemData)
           }.insertSeparators { before: AdapterData.data?, after: AdapterData.data? ->
                if(after == null) {
                    // We're at end of the list
                    null
                }
                else if(before == null || before.data.effectiveDate.dayOfMonth != after.data.effectiveDate.dayOfMonth) {
                    AdapterData.SeparatorItem(after.data.effectiveDate)
                } else {
                    null
                }
            }
        }
    }

}
/*

    fun filteredTransaction(list : List<TransactionHistory>): Map<LocalDate, List<TransactionHistory>> {
        return list.sortedByDescending {
            it.effectiveDate
        }.groupBy {
            it.effectiveDate
        }.mapValues {
            it.value.sortedWith(
                compareBy { it.isPending == false }
            )
        }
    }
 */
/*
        try {
            withContext(Dispatchers.IO) {
                transactionAccountProvider.getTransaction("1")
            }
        } catch (e : Exception) {
            when(e) {
                is HttpException ->
                    when(e.code()) {
                        HTTP_UNAVAILABLE -> throw TransactionServerDown
                        HTTP_INTERNAL_ERROR -> throw TransactionServerError
                        else -> throw UnexpectedTransactionException
                    }
                else -> throw UnexpectedTransactionException
            }
        }
 */