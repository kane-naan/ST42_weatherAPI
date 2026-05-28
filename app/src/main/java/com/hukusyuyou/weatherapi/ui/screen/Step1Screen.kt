package com.hukusyuyou.weatherapi.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hukusyuyou.weatherapi.ui.viewmodel.WeatherUiState
import com.hukusyuyou.weatherapi.ui.viewmodel.WeatherViewModel

@Composable
fun Step1Screen(
    viewModel: WeatherViewModel,
    modifier: Modifier = Modifier
){
    val uiState by viewModel.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "天気を取得 Ver1.0 (MVVM)",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onFetchWeatherClick() }
        ) {
            Text(text = "天気を取得")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (val state = uiState) {
            is WeatherUiState.Idle -> {
                // 初期状態の表示
                Text(
                    text = "まだ取得していません",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            is WeatherUiState.Loading -> {
                CircularProgressIndicator()
            }
            is WeatherUiState.Success -> {
                val temp = state.weather.currentWeather.temperature
                val wind = state.weather.currentWeather.windspeed

                Text(
                    text = "現在の気温: ${temp}℃ \n風速: ${wind} m/s",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            is WeatherUiState.Error -> {
                Text(
                    text = state.message,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}