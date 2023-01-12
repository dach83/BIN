package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.github.dach83.bin.feature.search.presentation.*

@Composable
fun CardDetailsList(uiState: SearchUiState) {
    LazyColumn(modifier = Modifier.testTag(SearchScreenTags.CARD_DETAILS_LIST)) {
        cardSection(uiState.cardDetails)
        numberSection(uiState.cardDetails)
        countrySection(uiState.cardDetails)
        bankSection(uiState.cardDetails)
    }
}
