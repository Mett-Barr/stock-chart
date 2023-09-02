package com.example.stockchart.ui.component.river_chart

import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class RiverChartFormatter(private val boundaryDataSet: LineDataSet?) : IFillFormatter {
    override fun getFillLinePosition(
        dataSet: ILineDataSet,
        dataProvider: LineDataProvider
    ): Float {
        return 0f
    }

    fun getFillLineBoundary(): List<Entry>? {
        return boundaryDataSet?.values
    }
}