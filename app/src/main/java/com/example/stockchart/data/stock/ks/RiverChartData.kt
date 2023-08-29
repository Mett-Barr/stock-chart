package com.example.stockchart.data.stock.ks


import com.example.stockchart.data.stock.core.RiverChartCore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//todo
@Serializable
data class RiverChartData(
    @SerialName("平均本淨比")
    override val averageNetValueRatio: String,
    @SerialName("平均本益比")
    override val averagePriceEarningsRatio: String,
    @SerialName("年月")
    override val yearMonth: String,
    @SerialName("月平均收盤價")
    override val monthlyAverageClosingPrice: String,
    @SerialName("月近一季本淨比")
    override val monthlyRecentQuarterNetValueRatio: String,
    @SerialName("月近四季本益比")
    override val monthlyLastFourQuartersPERatio: String,
    @SerialName("本淨比股價基準")
    override val netValueBasedPriceStandard: List<String>,
    @SerialName("本益比股價基準")
    override val priceEarningsBasedPriceStandard: List<String>,
    @SerialName("近3年年複合成長")
    override val recentThreeYearsCompoundGrowth: String,
    @SerialName("近一季BPS")
    override val recentQuarterBPS: String,
    @SerialName("近四季EPS")
    override val lastFourQuartersEPS: String
) : RiverChartCore