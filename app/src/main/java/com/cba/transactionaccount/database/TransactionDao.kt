package com.cba.transactionaccount.database

import androidx.paging.PagingSource
import androidx.room.*


/**
 * A Dao should provide the methods that the rest of the app uses to interact with
 * data entity in the transaction table
 */

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transaction_history_table")
    fun getTransactions(): PagingSource<Int, TransactionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(transactionHistory: List<TransactionEntity>)

    @Delete
    fun delete(transactionHistory: TransactionEntity)

    @Query("DELETE FROM transaction_history_table")
    suspend fun clearAllTransactions()
}