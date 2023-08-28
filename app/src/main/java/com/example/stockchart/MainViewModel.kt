package com.example.stockchart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockchart.data.SettingsDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
) : ViewModel() {
    /** UI State */
    private val _composeModeState = MutableStateFlow(IsComposeMode.NULL)
    val composeModeState: StateFlow<IsComposeMode> = _composeModeState

    init {
        fetchComposeMode()
    }

    private fun fetchComposeMode() {
        viewModelScope.launch {
            delay(3000)
            val isComposeMode = settingsDataStore.getComposeMode()
            _composeModeState.value = IsComposeMode.getEnumState(isComposeMode)
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