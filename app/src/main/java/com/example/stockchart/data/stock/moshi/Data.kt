package com.example.stockchart.data.stock.moshi


import com.example.stockchart.data.stock.core.StockInfoCore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "同業本淨比中位數")
    override val industryNetValueMedian: String,
    @Json(name = "同業本益比中位數")
    override val industryPERatioMedian: String,
    @Json(name = "平均本淨比")
    override val averageNetValueRatio: String,
    @Json(name = "平均本益比")
    override val averagePriceEarningsRatio: String,
    @Json(name = "本淨比基準")
    override val netValueStandard: List<String>,
    @Json(name = "本淨比股價評估")
    override val netValuePriceEvaluation: String,
    @Json(name = "本益成長比")
    override val priceEarningsGrowthRatio: String,
    @Json(name = "本益比基準")
    override val priceEarningsStandard: List<String>,
    @Json(name = "本益比股價評估")
    override val priceEarningsEvaluation: String,
    @Json(name = "河流圖資料")
    override val riverChartData: List<RiverChartData>,
    @Json(name = "目前本淨比")
    override val currentNetValueRatio: String,
    @Json(name = "目前本益比")
    override val currentPriceEarningsRatio: String,
    @Json(name = "股票代號")
    override val stockCode: String,
    @Json(name = "股票名稱")
    override val stockName: String
) : StockInfoCore