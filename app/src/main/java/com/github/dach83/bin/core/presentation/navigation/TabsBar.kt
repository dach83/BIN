package com.github.dach83.bin.core.presentation

import androidx.compose.material3.Icon
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.dach83.bin.core.presentation.navigation.TabItem

@Composable
fun TabsBar(
    tabs: List<TabItem>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                selected = selectedTabIndex == index,
                icon = {
                    Icon(
                        imageVector = tabItem.icon,
                        contentDescription = stringResource(tabItem.title)
                    )
                },
                text = {
                    Text(text = stringResource(id = tabItem.title))
                },
                onClick = {
                    onTabClick(index)
                }
            )
        }
    }
}
