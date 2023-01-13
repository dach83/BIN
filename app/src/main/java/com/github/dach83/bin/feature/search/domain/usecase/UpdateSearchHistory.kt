package com.github.dach83.bin.feature.search.domain.usecase

interface UpdateSearchHistory {
    suspend operator fun invoke(cardNumber: String)
}
