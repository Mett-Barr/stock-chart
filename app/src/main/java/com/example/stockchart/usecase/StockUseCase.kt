package com.example.stockchart.usecase

import com.example.stockchart.data.stock.core.RiverChartCore
import com.example.stockchart.data.stock.core.StockDataCore
import com.example.stockchart.ui.component.river_chart.RiverEntry
import com.example.stockchart.ui.component.river_chart.UiState
import com.example.stockchart.util.DateUtil
import com.github.mikephil.charting.data.Entry

object StockUseCase {

    fun getUiState(stockDataCore: StockDataCore?): UiState {
        if (stockDataCore == null) return UiState.Loading

        val riverChartData = stockDataCore.data.first().riverChartData
        val riverEntries = convertToRiverEntries(riverChartData)
        val stockPriceEntries = convertToStockPriceEntries(riverChartData)
        val oldestDate = riverChartData.last().yearMonth
        val dateList = riverChartData.reversed().map { DateUtil.convertToYearMonthFormat(it.yearMonth) }
        val priceEarningsStandard = stockDataCore.data.first().priceEarningsStandard.reversed()

        return UiState.Success(
            riverEntries,
            stockPriceEntries,
            oldestDate,
            dateList,
            priceEarningsStandard
        )
    }

    private fun convertToRiverEntries(data: List<RiverChartCore>): List<RiverEntry> {

        if (data.isEmpty()) return emptyList()

        val size = data.first().netValueBasedPriceStandard.size
        return (0 until size).map { index ->
            val upperEntries = mutableListOf<Entry>()
            val lowerEntries = mutableListOf<Entry>()
            data.reversed().forEachIndexed { idx, riverChartCore ->
                upperEntries.add(Entry(idx.toFloat(), riverChartCore.priceEarningsBasedPriceStandard[index].toFloat()))
                if (index == 0) {
                    lowerEntries.add(Entry(idx.toFloat(), riverChartCore.priceEarningsBasedPriceStandard[index].toFloat() - 10f))
                } else {
                    lowerEntries.add(Entry(idx.toFloat(), riverChartCore.priceEarningsBasedPriceStandard[index - 1].toFloat()))
                }
            }
            RiverEntry(upperEntries, lowerEntries)
        }.reversed()
    }

    private fun convertToStockPriceEntries(data: List<RiverChartCore>): List<Entry> {

        if (data.isEmpty()) return emptyList()

        return data.reversed().mapIndexed { index, it ->
            Entry(index.toFloat(), it.monthlyAverageClosingPrice.toFloat())
        }
    }
}