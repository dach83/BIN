package com.github.dach83.bin.core.data.local.room.mapper

import com.github.dach83.bin.core.data.local.room.entity.CardEntity
import com.github.dach83.bin.core.domain.model.CardQuery

fun CardQuery.toCardEntity(saveTime: Long) = CardEntity(
    cardNumber = cardNumber,
    saveTime = saveTime
)
