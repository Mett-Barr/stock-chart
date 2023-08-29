package com.example.stockchart.data.stock.gson


import com.example.stockchart.data.stock.core.StockDataCore
import com.google.gson.annotations.SerializedName

data class GsonData(
    @SerializedName("data")
    override val `data`: List<Data>
) : StockDataCore