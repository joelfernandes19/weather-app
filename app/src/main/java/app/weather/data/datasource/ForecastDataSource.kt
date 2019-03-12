package app.weather.data.datasource

import app.weather.domain.model.CurrentWeather
import app.weather.domain.model.ForecastData
import io.reactivex.Single

interface ForecastRemoteDataSource {

    fun getFiveDayForecast(city : String, country : String) : Single<ForecastData>
    fun getCurrentWeather(city: String, country: String) : Single<CurrentWeather>

}

interface ForecastLocalDataSource {

    fun getFiveDayForecast(city : String, country : String) : Single<ForecastData>
    fun setFiveDayForecast(city : String, country : String, data : ForecastData)

    fun getCurrentWeather(city: String, country: String) : Single<CurrentWeather>
    fun setCurrentWeather(city : String, country : String, data : CurrentWeather)

}