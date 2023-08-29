package com.example.stockchart.data.stock.gson


import com.example.stockchart.data.stock.core.StockInfoCore
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("同業本淨比中位數")
    override val industryNetValueMedian: String,
    @SerializedName("同業本益比中位數")
    override val industryPERatioMedian: String,
    @SerializedName("平均本淨比")
    override val averageNetValueRatio: String,
    @SerializedName("平均本益比")
    override val averagePriceEarningsRatio: String,
    @SerializedName("本淨比基準")
    override val netValueStandard: List<String>,
    @SerializedName("本淨比股價評估")
    override val netValuePriceEvaluation: String,
    @SerializedName("本益成長比")
    override val priceEarningsGrowthRatio: String,
    @SerializedName("本益比基準")
    override val priceEarningsStandard: List<String>,
    @SerializedName("本益比股價評估")
    override val priceEarningsEvaluation: String,
    @SerializedName("河流圖資料")
    override val riverChartData: List<RiverChartData>,
    @SerializedName("目前本淨比")
    override val currentNetValueRatio: String,
    @SerializedName("目前本益比")
    override val currentPriceEarningsRatio: String,
    @SerializedName("股票代號")
    override val stockCode: String,
    @SerializedName("股票名稱")
    override val stockName: String
) : StockInfoCore
