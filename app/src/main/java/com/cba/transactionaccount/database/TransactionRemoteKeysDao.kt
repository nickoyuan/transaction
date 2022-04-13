package com.cba.transactionaccount.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionRemoteKeysDao {
    @Query("SELECT * FROM transaction_remote_key_table WHERE id =:id")
    suspend fun getRemoteKeys(id: String): TransactionRemoteKeysEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<TransactionRemoteKeysEntity>)

    @Query("DELETE FROM transaction_remote_key_table")
    suspend fun deleteAll()
}