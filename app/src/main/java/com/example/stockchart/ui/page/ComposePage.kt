package com.example.stockchart.ui.page

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.stockchart.MainViewModel
import com.example.stockchart.ui.theme.StockChartTheme

@Composable
fun ComposePage(
    viewModel: MainViewModel,
    toggleComposeMode: () -> Unit
) {

    val context = LocalContext.current

    val text by viewModel.text.collectAsState()

    val stock by viewModel.stock.collectAsState()
    StockChartTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    toggleComposeMode()

                    val clip = ClipData.newPlainText("label", text)
                    val clipboard =
                        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    clipboard.setPrimaryClip(clip)
                },
            color = MaterialTheme.colorScheme.background
        ) {
            if (stock != null) {
                Column {
                    Text(text = stock!!.data.first().riverChartData[0].netValueBasedPriceStandard.toString())
                    Text(text = stock!!.data.first().riverChartData[0].priceEarningsBasedPriceStandard.toString())
                }
            }
        }
    }
}