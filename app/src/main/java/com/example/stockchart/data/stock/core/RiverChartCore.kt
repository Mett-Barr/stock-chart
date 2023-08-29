package com.example.stockchart.data.stock.core

interface RiverChartCore {
    val averageNetValueRatio: String
    val averagePriceEarningsRatio: String
    val yearMonth: String
    val monthlyAverageClosingPrice: String
    val monthlyRecentQuarterNetValueRatio: String
    val monthlyLastFourQuartersPERatio: String
    val netValueBasedPriceStandard: List<String>
    val priceEarningsBasedPriceStandard: List<String>
    val recentThreeYearsCompoundGrowth: String?
    val recentQuarterBPS: String
    val lastFourQuartersEPS: String
}