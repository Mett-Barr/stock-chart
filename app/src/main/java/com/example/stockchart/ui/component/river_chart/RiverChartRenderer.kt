package com.example.stockchart.ui.component.river_chart

import android.graphics.Canvas
import android.graphics.Path
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.renderer.LineChartRenderer
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.ViewPortHandler

class RiverChartRenderer(
    chart: LineDataProvider,
    animator: ChartAnimator,
    viewPortHandler: ViewPortHandler
) : LineChartRenderer(chart, animator, viewPortHandler) {

    private fun generateFilledPath(
        dataSet: ILineDataSet,
        startIndex: Int,
        endIndex: Int,
        outputPath: Path
    ) {
        val boundaryEntry =
            (dataSet.fillFormatter as RiverChartFormatter).getFillLineBoundary() ?: return

        val phaseY = mAnimator.phaseY
        val filled = outputPath
        filled.reset()

        val entry = dataSet.getEntryForIndex(startIndex)
        filled.moveTo(entry.x, boundaryEntry[0].y)
        filled.lineTo(entry.x, entry.y * phaseY)

        var currentEntry: Entry?
        for (x in startIndex + 1..endIndex) {
            currentEntry = dataSet.getEntryForIndex(x)
            filled.lineTo(currentEntry.x, currentEntry.y * phaseY)
        }

        for (x in endIndex downTo startIndex) {
            val previousEntry = boundaryEntry[x]
            filled.lineTo(previousEntry.x, previousEntry.y * phaseY)
        }
        filled.close()
    }

    override fun drawLinearFill(
        c: Canvas,
        dataSet: ILineDataSet,
        trans: Transformer,
        bounds: XBounds
    ) {
        val filledPath = Path()
        val startingIndex = bounds.min
        val endingIndex = bounds.min + bounds.range
        generateFilledPath(dataSet, startingIndex, endingIndex, filledPath)
        trans.pathValueToPixel(filledPath)

        val drawable = dataSet.fillDrawable
        if (drawable != null) {
            drawFilledPath(c, filledPath, drawable)
        } else {
            drawFilledPath(c, filledPath, dataSet.fillColor, dataSet.fillAlpha)
        }
    }
}