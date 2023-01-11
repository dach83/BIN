package com.github.dach83.bin.feature.search.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = koinViewModel()) {
    val uiState = searchViewModel.uiState
    Text(
        text = "Search screen"
    )
}
