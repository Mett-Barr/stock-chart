package com.example.stockchart.data.stock.gson


import com.example.stockchart.data.stock.core.RiverChartCore
import com.google.gson.annotations.SerializedName

data class RiverChartData(
    @SerializedName("平均本淨比")
    override val averageNetValueRatio: String,
    @SerializedName("平均本益比")
    override val averagePriceEarningsRatio: String,
    @SerializedName("年月")
    override val yearMonth: String,
    @SerializedName("月平均收盤價")
    override val monthlyAverageClosingPrice: String,
    @SerializedName("月近一季本淨比")
    override val monthlyRecentQuarterNetValueRatio: String,
    @SerializedName("月近四季本益比")
    override val monthlyLastFourQuartersPERatio: String,
    @SerializedName("本淨比股價基準")
    override val netValueBasedPriceStandard: List<String>,
    @SerializedName("本益比股價基準")
    override val priceEarningsBasedPriceStandard: List<String>,
    @SerializedName("近3年年複合成長")
    override val recentThreeYearsCompoundGrowth: String,
    @SerializedName("近一季BPS")
    override val recentQuarterBPS: String,
    @SerializedName("近四季EPS")
    override val lastFourQuartersEPS: String
) : RiverChartCore