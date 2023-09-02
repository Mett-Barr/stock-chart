package com.example.stockchart.ui.component.river_chart

import com.github.mikephil.charting.data.Entry
import kotlin.math.roundToInt

sealed class UiState {
    data class Success(
        val riverEntries: List<RiverEntry>,
        val stockPriceEntries: List<Entry>,
        val oldestDate: String,
        val dateList: List<String>,
        val priceEarningsStandard: List<String>
    ) : UiState() {
        fun getStockPrice(index: Int): Int {
            return this.stockPriceEntries[index].y.roundToInt()
        }
    }

    data object Loading : UiState()

    data class Error(val message: String = "An error occurred") : UiState()

    fun isSuccess(lambda: Success.() -> Unit) {
        if (this is Success) lambda(this)
    }
}
