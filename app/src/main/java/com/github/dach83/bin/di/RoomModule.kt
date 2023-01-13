package com.github.dach83.bin.di

import androidx.room.Room
import com.github.dach83.bin.core.data.local.room.CardDao
import com.github.dach83.bin.core.data.local.room.CardDatabase
import org.koin.dsl.module

val roomModule = module {

    single<CardDatabase> {
        Room.databaseBuilder(
            context = get(),
            klass = CardDatabase::class.java,
            name = "card-db"
        )
            .fallbackToDestructiveMigration() // todo: delete
            .build()
    }

    single<CardDao> {
        get<CardDatabase>().cardDao()
    }
}
