package com.hukusyuyou.weatherapi.data.repository

import com.hukusyuyou.weatherapi.data.api.RetrofitInstance
import com.hukusyuyou.weatherapi.data.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepository {
    suspend fun fetchWeather(): WeatherResponse? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.api.getCurrentWeather(
                    latitude = 35.6762,
                    longitude = 139.6503
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}