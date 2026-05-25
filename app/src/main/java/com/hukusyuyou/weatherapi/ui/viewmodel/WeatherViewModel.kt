package com.hukusyuyou.weatherapi.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hukusyuyou.weatherapi.data.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _weatherInfo = mutableStateOf("まだ取得していません")
    val weatherInfo: State<String> = _weatherInfo

    fun onFetchWeatherClick() {
        _weatherInfo.value = "取得中..."
        viewModelScope.launch {
            val response = repository.fetchWeather()
            if (response != null) {
                val temp = response.currentWeather.temperature
                val wind = response.currentWeather.windspeed
                _weatherInfo.value = "現在の気温: ${temp}℃ \n風速: ${wind} m/s"
            } else {
                _weatherInfo.value = "エラーだっぴょーん！！！！！"
            }
        }
    }
}