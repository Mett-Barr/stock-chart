package com.example.stockchart.ui.page

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockchart.MainViewModel
import com.example.stockchart.ui.theme.StockChartTheme

@Composable
fun ComposePage(
    viewModel: MainViewModel,
    toggleComposeMode: () -> Unit
) {
    StockChartTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable { toggleComposeMode() },
            color = MaterialTheme.colorScheme.background
        ) {
            Text(text = viewModel.hashCode().toString())
        }
    }
}