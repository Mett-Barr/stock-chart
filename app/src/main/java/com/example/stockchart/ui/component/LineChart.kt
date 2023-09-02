package com.example.stockchart.ui.component

import com.example.stockchart.R
import com.example.stockchart.ui.component.river_chart.RiverChartFormatter
import com.example.stockchart.ui.component.river_chart.RiverChartRenderer
import com.example.stockchart.ui.component.river_chart.RiverEntry
import com.example.stockchart.ui.component.river_chart.UiState
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlin.math.roundToInt

fun displayRiverCharts(lineChart: LineChart, uiState: UiState.Success, initSelectedEntry: (Entry) -> Unit) {
    val allDataSets = mutableListOf<ILineDataSet>()
    val riverColors = lineChart.context.resources.getIntArray(R.array.river_chart_colors_array)

    uiState.riverEntries.forEachIndexed { index, riverEntry ->
        val lineData = createLineDataFromRiverEntry(riverEntry, index, riverColors[index])
        allDataSets.addAll(lineData.dataSets)
    }

    val stockPriceDataSet = getStockPriceDataSet(lineChart, uiState.stockPriceEntries)
    allDataSets.add(stockPriceDataSet)

    riverChartSettings(lineChart, uiState.dateList)


    // Set the renderer once here.
    lineChart.renderer = RiverChartRenderer(lineChart, lineChart.animator, lineChart.viewPortHandler)

    val combinedLineData = LineData(allDataSets)
    lineChart.data = combinedLineData


    // Highlight the last entry of the last dataset
    val lastEntry = uiState.stockPriceEntries[uiState.stockPriceEntries.lastIndex]
    // 假設你知道這個數據集在`allDataSets`中的索引為`datasetIndex`
    val datasetIndex = allDataSets.lastIndex // 你應該知道這個數據集的確切索引或如何取得它
    val highlight = Highlight(lastEntry.x, lastEntry.y, datasetIndex)
    lineChart.onTouchListener.setLastHighlighted(null)
    lineChart.highlightValue(highlight)
    lineChart.moveViewToX(uiState.stockPriceEntries.last().x)
    lineChart.isDragYEnabled = false

    lineChart.axisRight.valueFormatter = object : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return if (value == axis?.axisMinimum) {
                "$value 元"
            } else {
                value.toString()
            }
        }
    }

    lineChart.invalidate()

    initSelectedEntry(uiState.stockPriceEntries.last())
}

fun createLineDataFromRiverEntry(riverEntry: RiverEntry, index: Int, riverColor: Int): LineData {

    val upperDataSet = LineDataSet(riverEntry.upperEntries, "Upper Line $index").apply {
        color = riverColor
        lineWidth = 2.5f
        setDrawValues(false)
        setDrawCircles(false)
//        valueTextColor = dataSetColor
//        setDrawHorizontalHighlightIndicator(false)
        isHighlightEnabled = false
    }

    val lowerDataSet = LineDataSet(riverEntry.lowerEntries, "Lower Line $index").apply {
        color = riverColor
        lineWidth = 2.5f
        setDrawValues(false)
        setDrawCircles(false)
//        valueTextColor = dataSetColor

        // Apply the fill formatting directly within this scope
        fillFormatter = RiverChartFormatter(upperDataSet)
        fillColor = riverColor
        fillAlpha = 255
        setDrawFilled(true)
//        setDrawHorizontalHighlightIndicator(false)
        isHighlightEnabled = false
    }


    val dataSets = listOf(upperDataSet, lowerDataSet)
    return LineData(dataSets)
}

private fun riverChartSettings(lineChart: LineChart, dateList: List<String>) {
    // color settings
    val chartTextColor = lineChart.context.getColor(R.color.river_chart_text_color)
    val chartGridColor = lineChart.context.getColor(R.color.river_chart_grid_color)

    // X-axis settings
    lineChart.xAxis.apply {
        position = XAxis.XAxisPosition.BOTTOM
        textColor = chartTextColor
        gridColor = chartGridColor

        // X軸的間隔就會被設定為1
        granularity = 1f
        isGranularityEnabled = true

        valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                // 將value轉換成日期，例如:
                return dateList[value.roundToInt()]
            }
        }
    }

    // Y-axis settings
    lineChart.axisRight.apply {
        isEnabled = true
        textColor = chartTextColor
        gridColor = chartGridColor
    }
    lineChart.axisLeft.isEnabled = false

    // Legend settings
    // 原本要用原生實現，但格式不太對
    lineChart.legend.isEnabled = false
//    lineChart.legend.apply {
//        textColor = lineChart.context.getColor(R.color.river_chart_text_color)
//        verticalAlignment = Legend.LegendVerticalAlignment.TOP
//        isWordWrapEnabled = true
//        maxSizePercent = 1f
//    }
}

private fun getStockPriceDataSet(lineChart: LineChart, stockPriceEntries: List<Entry>): LineDataSet {
    // 新增的股價走勢折線部分
    val stockPriceColor = lineChart.context.getColor(R.color.stock_line_color)
    val stockPriceDataSet = LineDataSet(stockPriceEntries, "Stock Price").apply {
        color = stockPriceColor
        lineWidth = 2.5f
        setDrawValues(false)
        setDrawCircles(false) // 無圓點
        setDrawFilled(false)  // 無填充
        setDrawHorizontalHighlightIndicator(false)
        highLightColor = lineChart.context.getColor(R.color.selected_line_color)
        isHighlightEnabled = true
    }
    return stockPriceDataSet
}