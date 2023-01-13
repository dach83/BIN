package com.github.dach83.bin.feature.search.domain.usecase

import com.github.dach83.bin.core.domain.model.CardQuery

interface UpdateSearchHistory {
    suspend operator fun invoke(cardQuery: CardQuery)
}
