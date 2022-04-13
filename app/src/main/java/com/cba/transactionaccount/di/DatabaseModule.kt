package com.cba.transactionaccount.di

import android.content.Context
import androidx.room.Room
import com.cba.transactionaccount.database.AppDatabase
import com.cba.transactionaccount.database.TransactionDao
import com.cba.transactionaccount.database.TransactionRemoteKeysDao
import com.cba.transactionaccount.util.Constants.TRANSACTION_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTransactionDao(appDatabase: AppDatabase): TransactionDao {
        return appDatabase.transactionDao()
    }

    @Provides
    fun provideTransactionRemoteKeyDao(appDatabase: AppDatabase): TransactionRemoteKeysDao {
        return appDatabase.transactionRemoteKeyDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            TRANSACTION_DATABASE
        ).build()
    }

}