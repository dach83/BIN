package com.github.dach83.bin.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.github.dach83.bin.core.presentation.ui.theme.BINTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BINTheme {
                MainScreen()
            }
        }
    }
}
