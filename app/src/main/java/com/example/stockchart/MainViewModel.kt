package com.example.stockchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockchart.data.settings.SettingsDataStore
import com.example.stockchart.data.stock.core.StockDataCore
import com.example.stockchart.network.RequestMode
import com.example.stockchart.network.StockRepository
import com.example.stockchart.ui.component.river_chart.UiState
import com.example.stockchart.usecase.StockUseCase
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
) : ViewModel() {
    private val stockRepository = StockRepository

    /** UI State */
    private val _composeModeState = MutableStateFlow(IsComposeMode.NULL)
    val composeModeState: StateFlow<IsComposeMode> = _composeModeState

    init {
//        fetchComposeMode()
        requestStockData()
    }

    private fun fetchComposeMode() {
        viewModelScope.launch {
            val isComposeMode = settingsDataStore.getComposeMode()
            _composeModeState.value = isComposeMode
        }
    }

    private fun saveComposeMode(mode: Boolean) {
        viewModelScope.launch {
            settingsDataStore.saveComposeMode(mode)
            _composeModeState.value = IsComposeMode.getEnumState(mode)
        }
    }

    fun toggleComposeMode() {
        val newMode = when (_composeModeState.value) {
            IsComposeMode.TRUE -> false
            IsComposeMode.FALSE -> true
            IsComposeMode.NULL -> true // Assuming default action for NULL is to set it to true
        }
        saveComposeMode(newMode)
    }


    /** Request Mode */
    private val _requestMode = MutableStateFlow(RequestMode.GSON_RETROFIT)
    val requestMode: StateFlow<RequestMode> = _requestMode

    fun fetchRequestMode() {
        viewModelScope.launch {

        }
    }

    fun saveRequestMode(requestMode: RequestMode) {

    }

    // test
    private val _text = MutableStateFlow("")
    val text: StateFlow<String> = _text
    private val _stock = MutableStateFlow<StockDataCore?>(null)
    val stock: StateFlow<StockDataCore?> = _stock

    val uiState: StateFlow<UiState> = _stock.map { stockData ->
        StockUseCase.getUiState(stockData)
    }.stateIn(viewModelScope, SharingStarted.Lazily, UiState.Loading)

    private val _selectedPosition = MutableStateFlow<Int?>(null)
    val selectedPosition: StateFlow<Int?> = _selectedPosition

    fun onSelect(entry: Entry) {
        uiState.value.isSuccess { _selectedPosition.value = stockPriceEntries.indexOf(entry) }
    }

    private fun requestStockData() {
        viewModelScope.launch(Dispatchers.IO) {
            // 讓圈圈轉一下🤣
            delay(1500)
            _stock.value = stockRepository.fetchData(requestMode = RequestMode.KS_KTOR)
        }
    }
}

enum class IsComposeMode {
    TRUE, FALSE, NULL;

    companion object {
        fun getBooleanState(state: IsComposeMode): Boolean? {
            return when (state) {
                TRUE -> true
                FALSE -> false
                NULL -> null
            }
        }

        fun getEnumState(state: Boolean?): IsComposeMode {
            return when (state) {
                true -> TRUE
                false -> FALSE
                null -> NULL
            }
        }
    }
}