package com.github.dach83.bin.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.dach83.bin.core.data.local.room.entity.CardEntity

@Dao
interface CardDao {
    @Query("SELECT EXISTS(SELECT * FROM card WHERE cardNumber = :cardNumber)")
    suspend fun contains(cardNumber: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cardEntity: CardEntity)
}
