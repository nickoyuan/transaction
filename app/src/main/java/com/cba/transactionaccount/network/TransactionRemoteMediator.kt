package com.cba.transactionaccount.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.cba.transactionaccount.model.TransactionHistory
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TransactionRemoteMediator @Inject constructor(private val transactionAccountProvider: TransactionAccountProvider) :
    PagingSource<Int, TransactionHistory>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TransactionHistory> {
        try {
            val page = params.key ?: 1
            val filteredTransactions = filteredTransaction(
                transactionAccountProvider.getTransaction(page).transactions
            )
            return LoadResult.Page(
                data = filteredTransactions,
                prevKey = page - 1,
                nextKey = page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TransactionHistory>): Int? {
        TODO("Not yet implemented")
    }

    private fun filteredTransaction(list : List<TransactionHistory>): List<TransactionHistory> {
        return list.sortedByDescending {
            it.effectiveDate
        }.groupBy {
            it.effectiveDate.dayOfMonth
        }.mapValues {
            it.value.sortedWith(
                compareBy { it.isPending == false }
            )
        }.flatMap {
            it.value
        }
    }
}