package com.cba.transactionaccount.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.cba.transactionaccount.database.AppDatabase
import com.cba.transactionaccount.database.TransactionEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalPagingApi
class TransactionAccountRepo
@Inject constructor(
    private val transactionAccountProvider: TransactionAccountProvider,
    private val transactionDatabase: AppDatabase
) {
    /**
     * PagingData will present null placeholders for not-yet-loaded content if two conditions are met:
    Its PagingSource can count all unloaded items (so that the number of nulls to present is known).
    enablePlaceholders is set to true
     */
    fun getTransactionData(): Flow<PagingData<TransactionEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            remoteMediator = TransactionRemoteMediator(
                transactionAccountProvider,
                transactionDatabase
            ),
            pagingSourceFactory = {
                transactionDatabase.transactionDao().getTransactions()
            }
        ).flow
    }
}


/*

2022-04-11 14:14:47.785 17124-17124/com.cba.transactionaccount E/AndroidRuntime: FATAL EXCEPTION: main
    Process: com.cba.transactionaccount, PID: 17124
    java.lang.IllegalStateException: Attempt to collect twice from pageEventFlow, which is an illegal operation. Did you forget to call Flow<PagingData<*>>.cachedIn(coroutineScope)?
        at androidx.paging.PageFetcherSnapshot$pageEventFlow$1.invokeSuspend(PageFetcherSnapshot.kt:82)
        at androidx.paging.PageFetcherSnapshot$pageEventFlow$1.invoke(Unknown Source:8)
        at androidx.paging.PageFetcherSnapshot$pageEventFlow$1.invoke(Unknown Source:4)
        at androidx.paging.CancelableChannelFlowKt$cancelableChannelFlow$1.invokeSuspend(CancelableChannelFlow.kt:30)
        at androidx.paging.CancelableChannelFlowKt$cancelableChannelFlow$1.invoke(Unknown Source:8)
        at androidx.paging.CancelableChannelFlowKt$cancelableChannelFlow$1.invoke(Unknown Source:4)
        at androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1$1.invokeSuspend(SimpleChannelFlow.kt:57)
        at androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1$1.invoke(Unknown Source:8)
        at androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1$1.invoke(Unknown Source:4)
        at kotlinx.coroutines.intrinsics.UndispatchedKt.startUndispatchedOrReturn(Undispatched.kt:89)
        at kotlinx.coroutines.CoroutineScopeKt.coroutineScope(CoroutineScope.kt:264)
        at androidx.paging.SimpleChannelFlowKt$simpleChannelFlow$1$1$producer$1.invokeSuspend(SimpleChannelFlow.kt:52)
 */
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