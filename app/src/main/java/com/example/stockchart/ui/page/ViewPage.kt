package com.example.stockchart.ui.page

import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.stockchart.MainViewModel
import com.example.stockchart.databinding.ActivityMainBinding
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

@Composable
fun ViewPage(
    viewModel: MainViewModel,
    toggleComposeMode: () -> Unit
) {

    val stock by viewModel.stock.collectAsState()

    AndroidView(
        factory = { context ->
            val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

            binding.root.setOnClickListener {
                toggleComposeMode()
            }

            stock?.let {
            }

            binding.chart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry?, h: Highlight?) {
                }

                override fun onNothingSelected() {
                    // 這裡會在用戶取消選擇時調用
                }
            })

            binding.chart.description.isEnabled = false




            binding.root
        },
        modifier = Modifier.fillMaxSize(),
        update = {
        }
    )
}