package com.github.dach83.bin.feature.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dach83.bin.feature.history.domain.usecase.CardsSearchHistory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(
    cardsSearchHistory: CardsSearchHistory
) : ViewModel() {

    val searchHistory = cardsSearchHistory().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = listOf()
    )
}
