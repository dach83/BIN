package com.github.dach83.bin.feature.search.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SearchScreen(searchViewModel: SearchViewModel = viewModel()) {
    val uiState = searchViewModel.uiState
    Text(
        text = "Search screen"
    )
}
