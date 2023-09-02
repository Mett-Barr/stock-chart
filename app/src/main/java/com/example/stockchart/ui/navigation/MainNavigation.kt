package com.example.stockchart.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockchart.IsComposeMode
import com.example.stockchart.MainViewModel
import com.example.stockchart.ui.page.ComposePage
import com.example.stockchart.ui.page.LoadingPage
import com.example.stockchart.ui.page.ViewPage

@Composable
fun MainNavigation(viewModel: MainViewModel = hiltViewModel()) {

    val navController = rememberNavController()
    val routeState by viewModel.composeModeState.collectAsState()

    NavHost(navController, startDestination = routeState.name, modifier = Modifier.fillMaxSize()) {
        composable(IsComposeMode.TRUE.name) {
            ComposePage(viewModel) {
                viewModel.toggleComposeMode()
            }
        }
        composable(IsComposeMode.FALSE.name) {
            ViewPage(viewModel = viewModel) {
                viewModel.toggleComposeMode()
            }
        }
        composable(IsComposeMode.NULL.name) {
            LoadingPage()
        }
    }
}