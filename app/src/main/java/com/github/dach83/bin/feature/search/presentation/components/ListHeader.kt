package com.github.dach83.bin.feature.search.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.listHeader(@StringRes header: Int) {
    stickyHeader {
        Text(
            text = stringResource(id = header).toUpperCase(Locale.current),
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .fillMaxWidth()
        )
    }
}
