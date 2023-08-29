package com.example.stockchart.network

import com.example.stockchart.data.stock.core.StockDataCore
import com.example.stockchart.network.ktor.KtorConfig
import com.example.stockchart.network.retrofit.RetrofitConfig
import com.example.stockchart.network.retrofit.getOrThrow

object StockRepository {
    private val retrofitConfig = RetrofitConfig
    private val ktorConfig = KtorConfig
    private const val defaultId = "2330"

    suspend fun fetchData(stockId: String = defaultId, requestMode: RequestMode): StockDataCore {
        return when (requestMode) {
            RequestMode.GSON_RETROFIT -> {
                retrofitConfig.fetchDataGson(stockId).getOrThrow()
            }
            RequestMode.MOSHI_RETROFIT -> {
                retrofitConfig.fetchDataMoshi(stockId).getOrThrow()
            }
            RequestMode.KS_KTOR -> {
                ktorConfig.fetchData(stockId)
            }
        }
    }
}

enum class RequestMode {
    GSON_RETROFIT,
    MOSHI_RETROFIT,
    KS_KTOR
}