package com.example.stockchart.data.stock.moshi


import com.example.stockchart.data.stock.core.RiverChartCore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RiverChartData(
    @Json(name = "平均本淨比")
    override val averageNetValueRatio: String,
    @Json(name = "平均本益比")
    override val averagePriceEarningsRatio: String,
    @Json(name = "年月")
    override val yearMonth: String,
    @Json(name = "月平均收盤價")
    override val monthlyAverageClosingPrice: String,
    @Json(name = "月近一季本淨比")
    override val monthlyRecentQuarterNetValueRatio: String,
    @Json(name = "月近四季本益比")
    override val monthlyLastFourQuartersPERatio: String,
    @Json(name = "本淨比股價基準")
    override val netValueBasedPriceStandard: List<String>,
    @Json(name = "本益比股價基準")
    override val priceEarningsBasedPriceStandard: List<String>,
    @Json(name = "近3年年複合成長")
    override val recentThreeYearsCompoundGrowth: String?,
    @Json(name = "近一季BPS")
    override val recentQuarterBPS: String,
    @Json(name = "近四季EPS")
    override val lastFourQuartersEPS: String
) : RiverChartCore