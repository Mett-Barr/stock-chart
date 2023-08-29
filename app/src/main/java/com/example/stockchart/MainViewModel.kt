package com.example.stockchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockchart.data.settings.SettingsDataStore
import com.example.stockchart.network.RequestMode
import com.example.stockchart.network.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        fetchComposeMode()
        test()
    }

    private fun fetchComposeMode() {
        viewModelScope.launch {
            delay(3000)
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
    private fun test() {
        viewModelScope.launch(Dispatchers.IO) {
            val stock = stockRepository.fetchData(requestMode = RequestMode.MOSHI_RETROFIT)
//                if (Random.nextBoolean()) {
//                    stockRepository.fetchData(requestMode = RequestMode.GSON_RETROFIT)
//                } else {
//                    stockRepository.fetchData(requestMode = RequestMode.MOSHI_RETROFIT)
//                }
            _text.value = stock.toString()
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