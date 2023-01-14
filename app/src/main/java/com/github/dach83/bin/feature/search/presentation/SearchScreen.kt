package com.github.dach83.bin.feature.search.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.dach83.bin.feature.search.presentation.components.CardDetailsList
import com.github.dach83.bin.feature.search.presentation.components.CardNumberEdit
import com.github.dach83.bin.feature.search.presentation.components.ErrorMessage
import com.github.dach83.bin.feature.search.presentation.components.LoadingIndicator
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    cardNumber: String,
    onChangeCardNumber: (cardNumber: String) -> Unit,
    viewModel: SearchViewModel = koinViewModel()
) {
    LaunchedEffect(key1 = cardNumber) {
        viewModel.changeCardNumber(cardNumber)
    }

    val uiState = viewModel.uiState
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        ) {
            ErrorMessage(uiState)
            LoadingIndicator(uiState)
        }
        CardNumberEdit(
            cardNumber = cardNumber,
            onValueChange = { newCardNumber ->
                viewModel.changeCardNumber(newCardNumber)
                onChangeCardNumber(newCardNumber)
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        CardDetailsList(uiState)
    }
}
