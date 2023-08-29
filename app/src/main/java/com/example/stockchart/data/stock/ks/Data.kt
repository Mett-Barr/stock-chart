package com.example.stockchart.data.stock.ks


import com.example.stockchart.data.stock.core.StockInfoCore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


//todo
@Serializable
data class Data(
    @SerialName("同業本淨比中位數")
    override val industryNetValueMedian: String,
    @SerialName("同業本益比中位數")
    override val industryPERatioMedian: String,
    @SerialName("平均本淨比")
    override val averageNetValueRatio: String,
    @SerialName("平均本益比")
    override val averagePriceEarningsRatio: String,
    @SerialName("本淨比基準")
    override val netValueStandard: List<String>,
    @SerialName("本淨比股價評估")
    override val netValuePriceEvaluation: String,
    @SerialName("本益成長比")
    override val priceEarningsGrowthRatio: String,
    @SerialName("本益比基準")
    override val priceEarningsStandard: List<String>,
    @SerialName("本益比股價評估")
    override val priceEarningsEvaluation: String,
    @SerialName("河流圖資料")
    override val riverChartData: List<RiverChartData>,
    @SerialName("目前本淨比")
    override val currentNetValueRatio: String,
    @SerialName("目前本益比")
    override val currentPriceEarningsRatio: String,
    @SerialName("股票代號")
    override val stockCode: String,
    @SerialName("股票名稱")
    override val stockName: String
) : StockInfoCore