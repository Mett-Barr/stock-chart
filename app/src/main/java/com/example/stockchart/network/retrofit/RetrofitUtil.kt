package com.example.stockchart.network.retrofit

import retrofit2.Response

fun <T> Response<T>.getOrThrow(): T {
    if (isSuccessful && body() != null) return body()!!
    //todo Null問題另外處理
    throw Exception("Error fetching data}")
}