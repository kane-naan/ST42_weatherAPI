package com.hukusyuyou.weatherapi.data.api

import com.hukusyuyou.weatherapi.data.model.WeatherResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") currentWeather: Boolean = true,
    ): WeatherResponse
//  ): ResponseBody // 加工されていないデータ
}