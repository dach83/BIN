package com.github.dach83.bin.feature.history.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.dach83.bin.core.domain.model.CardQuery

@Composable
fun QueryList(
    items: List<CardQuery>,
    onItemClick: (CardQuery) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items) { item ->
            QueryItem(
                item = item,
                onItemClick = onItemClick
            )
        }
    }
}
