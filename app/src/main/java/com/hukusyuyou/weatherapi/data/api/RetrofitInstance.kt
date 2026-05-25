package com.hukusyuyou.weatherapi.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.open-meteo.com/"
    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Gsonに変更
            .build()
            .create(WeatherApiService::class.java) // ここでApiServiceの実装が自動生成される
    }
}