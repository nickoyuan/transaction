package com.cba.transactionaccount.di

import com.cba.transactionaccount.network.TransactionAccountProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val BASE_URL = "https://www.dropbox.com/"

    @Singleton
    @Provides
    fun provideApiProvider(retrofit: Retrofit): TransactionAccountProvider = retrofit.create(TransactionAccountProvider::class.java)

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(BASE_URL)
        .build()
}