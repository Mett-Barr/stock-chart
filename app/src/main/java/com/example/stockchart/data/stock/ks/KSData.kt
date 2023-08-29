package com.example.stockchart.data.stock.ks


import com.example.stockchart.data.stock.core.StockDataCore
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KSData(
    @SerialName("data")
    override val `data`: List<Data>
) : StockDataCore