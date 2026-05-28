package com.hukusyuyou.weatherapi.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hukusyuyou.weatherapi.data.model.WeatherResponse
import com.hukusyuyou.weatherapi.data.repository.WeatherRepository
import kotlinx.coroutines.launch

sealed class WeatherUiState{
    object Idle: WeatherUiState() // 初期状態
    object Loading: WeatherUiState() // 読み込み中

    data class Success(val weather:WeatherResponse): WeatherUiState()
    data class Error(val message:String): WeatherUiState()

}

class WeatherViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val _uiState = mutableStateOf<WeatherUiState>(WeatherUiState.Idle)
    val uiState: State<WeatherUiState> = _uiState

    fun onFetchWeatherClick() {
        _uiState.value = WeatherUiState.Loading
        viewModelScope.launch {
            val response = repository.fetchWeather()
            if (response != null) {
                val temp = response.currentWeather.temperature
                val wind = response.currentWeather.windspeed
                _uiState.value = WeatherUiState.Success(response)
            } else {
                _uiState.value = WeatherUiState.Error("エラーだっぴょーん")
            }
        }
    }
}