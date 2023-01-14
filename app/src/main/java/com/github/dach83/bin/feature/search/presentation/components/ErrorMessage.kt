package com.github.dach83.bin.feature.search.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.github.dach83.bin.feature.search.presentation.SearchUiState

@Composable
fun ErrorMessage(uiState: SearchUiState) = Box {
    if (uiState.errorMessage != null) {
        Text(
            text = stringResource(id = uiState.errorMessage),
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.testTag(SearchScreenTags.ERROR_MESSAGE)
        )
        // TODO: Try again button
    }
}
