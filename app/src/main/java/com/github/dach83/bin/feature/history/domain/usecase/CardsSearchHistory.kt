package com.github.dach83.bin.feature.history.domain.usecase

import com.github.dach83.bin.core.domain.model.history.CardQuery
import kotlinx.coroutines.flow.Flow

interface CardsSearchHistory {
    operator fun invoke(): Flow<List<CardQuery>>
}
