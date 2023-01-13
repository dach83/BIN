package com.github.dach83.bin.feature.history.domain.usecase

import com.github.dach83.bin.core.domain.model.query.CardQuery
import kotlinx.coroutines.flow.Flow

interface SearchHistory {
    operator fun invoke(): Flow<List<CardQuery>>
}
