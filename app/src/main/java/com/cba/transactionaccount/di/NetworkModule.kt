package com.cba.transactionaccount.di

import com.cba.transactionaccount.data.TransactionAccountProvider
import com.cba.transactionaccount.util.LocalDateAdapter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.joda.time.LocalDate
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val BASE_URL = "https://www.dropbox.com/"

    @Singleton
    @Provides
    fun provideApiProvider(retrofit: Retrofit): TransactionAccountProvider = retrofit.createProvider()

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateAdapter()).create()
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(BASE_URL)
        .build()

    private inline fun <reified Interface> Retrofit.createProvider(): Interface = create(Interface::class.java)
}