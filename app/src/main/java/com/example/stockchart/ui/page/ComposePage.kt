package com.example.stockchart.ui.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.stockchart.MainViewModel
import com.example.stockchart.ui.theme.StockChartTheme

@Composable
fun ComposePage(
    viewModel: MainViewModel,
    toggleComposeMode: () -> Unit
) {

    val text by viewModel.text.collectAsState()
    StockChartTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable { toggleComposeMode() },
            color = MaterialTheme.colorScheme.background
        ) {
            Text(text = text)
        }
    }
}