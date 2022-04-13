package com.cba.transactionaccount.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.cba.transactionaccount.database.AppDatabase
import com.cba.transactionaccount.database.TransactionEntity
import com.cba.transactionaccount.database.TransactionRemoteKeysEntity

@ExperimentalPagingApi
class TransactionRemoteMediator(
    private val transactionAccountProvider: TransactionAccountProvider,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, TransactionEntity>() {

    private val transactionDao = appDatabase.transactionDao()
    private val transactionRemoteKeys = appDatabase.transactionRemoteKeyDao()

    /***
     * The LoadType informs the RemoteMediator whether
     * it needs to refresh the existing data or fetch additional data that needs to be
     * appended or prepended to the existing list.
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TransactionEntity>
    ): MediatorResult {

        val currentPage = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1   // Next Key - 1
            }

            LoadType.PREPEND -> {  // Start
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevPage = remoteKeys?.prevKey ?: return MediatorResult.Success(true)
                prevPage
            }
            LoadType.APPEND -> { // End
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextKey ?: return MediatorResult.Success(true)
                nextPage
            }
        }

        val transactionHistory =
            transactionAccountProvider.getTransaction(dl = currentPage).transactions

        val endOfPaginationReached = transactionHistory.isEmpty()

        val prevPage = if (currentPage == 1) null else currentPage - 1
        val nextPage = if (endOfPaginationReached) null else currentPage + 1

        appDatabase.withTransaction {
            if (loadType == LoadType.REFRESH) {
                transactionDao.clearAllTransactions()
                transactionRemoteKeys.deleteAll()
            }

            val remoteKeys = transactionHistory.map {
                TransactionRemoteKeysEntity(
                    it.id,
                    nextPage,
                    prevPage
                )
            }

            val transactionEntity = transactionHistory.map {
                TransactionEntity(
                    id = it.id,
                    amount = it.amount,
                    isPending = it.isPending,
                    description = it.description,
                    category = it.category,
                    effectiveDate = it.effectiveDate
                )
            }
            transactionDao.insertAll(transactionEntity)
            transactionRemoteKeys.insertAll(remoteKeys)
        }

        return MediatorResult.Success(true)
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, TransactionEntity>
    ): TransactionRemoteKeysEntity? {
        return state.anchorPosition?.let {
            state.closestItemToPosition(it)?.let { item ->
                transactionRemoteKeys.getRemoteKeys(id = item.id)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, TransactionEntity>
    ): TransactionRemoteKeysEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { item ->
                transactionRemoteKeys.getRemoteKeys(id = item.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, TransactionEntity>
    ): TransactionRemoteKeysEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { item ->
                transactionRemoteKeys.getRemoteKeys(id = item.id)
            }
    }

}