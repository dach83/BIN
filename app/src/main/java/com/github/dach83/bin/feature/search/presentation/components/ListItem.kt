package com.github.dach83.bin.feature.search.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp

fun LazyListScope.listItem(
    @StringRes header: Int,
    text: String,
    testTag: String
) {
    item {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = header).toLowerCase(Locale.current)
            )
            Text(
                text = text.ifEmpty { "?" },
                modifier = Modifier.testTag(testTag)
            )
        }
    }
}
