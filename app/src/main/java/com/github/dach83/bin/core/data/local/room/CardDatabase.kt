package com.github.dach83.bin.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.dach83.bin.core.data.local.room.entity.CardEntity

@Database(entities = [CardEntity::class], version = 1)
abstract class CardDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}
