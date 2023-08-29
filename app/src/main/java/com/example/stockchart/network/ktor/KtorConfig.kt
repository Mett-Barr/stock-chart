package com.example.stockchart.network.ktor

import com.example.stockchart.data.stock.ks.KSData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorConfig {
    private const val BASE_URL = "https://api.nstock.tw/"

    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    suspend fun fetchData(stockId: String): KSData {
        val response: HttpResponse = client.get("$BASE_URL/v2/per-river/interview?stock_id=$stockId")
        return response.body()
    }
}