package com.example.stockchart.network.retrofit

import android.util.Log
import com.example.stockchart.data.stock.gson.GsonData
import com.example.stockchart.data.stock.moshi.MoshiData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitConfig {

    private const val BASE_URL = "https://api.nstock.tw/"

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofitMoshi: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient) // test
        .build()

    private val retrofitGson: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiServiceMoshi: MoshiApiService = retrofitMoshi.create(MoshiApiService::class.java)
    private val apiServiceGson: GsonApiService = retrofitGson.create(GsonApiService::class.java)

    suspend fun fetchDataMoshi(stockId: String): Response<MoshiData> {
        Log.d("!!!", "Fetching data with Moshi for stock ID: $stockId")
        return apiServiceMoshi.getPerRiverData(stockId)
    }

    suspend fun fetchDataGson(stockId: String): Response<GsonData> {
        return apiServiceGson.getPerRiverData(stockId)
    }
}