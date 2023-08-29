package com.example.stockchart.network.retrofit

import com.example.stockchart.data.stock.gson.GsonData
import com.example.stockchart.data.stock.moshi.MoshiData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

// retrofit不接受在interface上使用類型參數
// 放棄此方案
//interface ApiService<T : StockDataCore> {
//    @GET("v2/per-river/interview")
//    suspend fun getPerRiverData(@Query("stock_id") stockId: String): Response<T>
//}

interface MoshiApiService {
    @GET("v2/per-river/interview")
    suspend fun getPerRiverData(@Query("stock_id") stockId: String): Response<MoshiData>
}

interface GsonApiService {
    @GET("v2/per-river/interview")
    suspend fun getPerRiverData(@Query("stock_id") stockId: String): Response<GsonData>
}