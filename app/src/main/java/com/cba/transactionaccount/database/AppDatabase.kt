package com.cba.transactionaccount.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [TransactionEntity::class, TransactionRemoteKeysEntity::class], version = 1)
@TypeConverters(DateTimeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao() : TransactionDao
    abstract fun transactionRemoteKeyDao() : TransactionRemoteKeysDao
}
