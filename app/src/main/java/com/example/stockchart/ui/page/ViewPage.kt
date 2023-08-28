package com.example.stockchart.ui.page

import android.content.Context
import android.view.LayoutInflater
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockchart.MainViewModel
import com.example.stockchart.databinding.ActivityMainBinding

@Composable
fun ViewPage(
    context: Context,
    viewModel: MainViewModel,
    toggleComposeMode: () -> Unit
) {
    AndroidView(
        factory = {
            val binding = ActivityMainBinding.inflate(LayoutInflater.from(context))

            binding.root.setOnClickListener {
                toggleComposeMode()
            }

            binding.root
        },
        modifier = Modifier.fillMaxSize()
    )
}