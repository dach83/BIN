package com.github.dach83.bin.feature.history.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.dach83.bin.core.domain.model.CardQuery
import com.github.dach83.bin.core.presentation.formatting.formatCardNumber

@Composable
fun QueryItem(
    item: CardQuery,
    onItemClick: (CardQuery) -> Unit
) {
    BasicTextField(
        value = item.cardNumber,
        enabled = false,
        textStyle = MaterialTheme.typography.titleLarge,
        onValueChange = {},
        visualTransformation = {
            formatCardNumber(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = MaterialTheme.shapes.extraSmall
            )
            .padding(16.dp)
            .clickable(onClick = {
                onItemClick(item)
            })
    )
}
