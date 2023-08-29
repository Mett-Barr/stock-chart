package com.example.stockchart.data.stock.moshi


import com.example.stockchart.data.stock.core.StockDataCore
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoshiData(
    @Json(name = "data")
    override val `data`: List<Data>
) : StockDataCore