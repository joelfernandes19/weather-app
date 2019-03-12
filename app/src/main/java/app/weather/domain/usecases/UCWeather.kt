package app.weather.domain.usecases

import app.weather.domain.model.CurrentWeather
import app.weather.domain.model.ForecastData
import app.weather.domain.repository.WeatherRepository
import io.reactivex.Single


/**
 * Uses cases of Weather app
 */
class UCWeather constructor(private val weatherRepository: WeatherRepository) {

    fun getFiveDaysForecast(city : String, country : String, refresh : Boolean): Single<ForecastData> =
        weatherRepository.getFiveDayForecast(city, country, refresh)

    fun getCurrentWeather(city : String, country : String, refresh : Boolean): Single<CurrentWeather> =
            weatherRepository.getCurrentWeather(city, country, refresh)
}