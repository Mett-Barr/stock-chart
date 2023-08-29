package com.example.stockchart.data.stock.core

interface StockInfoCore {
    val industryNetValueMedian: String
    val industryPERatioMedian: String
    val averageNetValueRatio: String
    val averagePriceEarningsRatio: String
    val netValueStandard: List<String>
    val netValuePriceEvaluation: String
    val priceEarningsGrowthRatio: String
    val priceEarningsStandard: List<String>
    val priceEarningsEvaluation: String
    val riverChartData: List<RiverChartCore>
    val currentNetValueRatio: String
    val currentPriceEarningsRatio: String
    val stockCode: String
    val stockName: String
}