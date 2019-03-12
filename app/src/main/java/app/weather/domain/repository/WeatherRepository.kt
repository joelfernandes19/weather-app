package app.weather.domain.repository

import app.weather.domain.model.CurrentWeather
import app.weather.domain.model.ForecastData
import io.reactivex.Single

interface WeatherRepository {
    fun getFiveDayForecast(city : String, country : String, refresh : Boolean) : Single<ForecastData>
    fun getCurrentWeather(city : String, country : String, refresh : Boolean) : Single<CurrentWeather>
}