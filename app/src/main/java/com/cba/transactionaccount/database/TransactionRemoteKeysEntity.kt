package com.cba.transactionaccount.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cba.transactionaccount.util.Constants.TRANSACTION_REMOTE_KEY_TABLE


@Entity(tableName = TRANSACTION_REMOTE_KEY_TABLE)
data class TransactionRemoteKeysEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val nextKey : Int?,
    val prevKey : Int?
)