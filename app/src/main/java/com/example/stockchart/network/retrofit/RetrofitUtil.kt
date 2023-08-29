package com.example.stockchart.network.retrofit

import retrofit2.Response

fun <T> Response<T>.getOrThrow(): T {
    if (isSuccessful && body() != null) return body()!!
    // Null問題之後處理
    throw Exception("Error fetching data}")
}
