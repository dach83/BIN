package com.github.dach83.bin.core.data.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Card")
data class CardEntity(
    @PrimaryKey
    val cardNumber: String,
    val saveTime: Long = Date().time
)
