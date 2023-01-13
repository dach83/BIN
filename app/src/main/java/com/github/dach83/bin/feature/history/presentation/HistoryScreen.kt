package com.github.dach83.bin.feature.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.dach83.bin.feature.history.presentation.components.NoQueries
import com.github.dach83.bin.feature.history.presentation.components.QueryList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
    showCardDetails: (String) -> Unit,
    viewModel: HistoryViewModel = koinViewModel()
) {
    val queries by viewModel.queries.collectAsState()
    if (queries.isEmpty()) {
        NoQueries()
    } else {
        QueryList(
            items = queries,
            onItemClick = { query ->
                showCardDetails(query.cardNumber)
            }
        )
    }
}
