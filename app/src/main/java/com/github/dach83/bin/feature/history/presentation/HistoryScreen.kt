package com.github.dach83.bin.feature.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.dach83.bin.core.domain.model.history.CardQuery
import com.github.dach83.bin.feature.history.presentation.components.EmptyHistory
import com.github.dach83.bin.feature.history.presentation.components.HistoryList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    onItemClick: (CardQuery) -> Unit,
    viewModel: HistoryViewModel = koinViewModel()
) {
    val searchHistory by viewModel.searchHistory.collectAsState()
    if (searchHistory.isEmpty()) {
        EmptyHistory()
    } else {
        HistoryList(
            items = searchHistory,
            onItemClick = onItemClick
        )
    }
}
