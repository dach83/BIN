package com.github.dach83.bin.di

import com.github.dach83.bin.feature.search.data.remote.BinLookupService
import com.github.dach83.bin.feature.search.data.remote.RemoteDataSource
import com.github.dach83.bin.feature.search.data.remote.RemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            )
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single<RemoteDataSource> {
        RemoteDataSourceImpl(get())
    }

    single<BinLookupService> {
        get<Retrofit>().create(BinLookupService::class.java)
    }
}
