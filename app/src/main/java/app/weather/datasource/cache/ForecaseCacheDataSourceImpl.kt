package app.weather.datasource.cache

import app.weather.cache.WeatherCache
import app.weather.data.datasource.ForecastLocalDataSource
import app.weather.domain.model.CurrentWeather
import app.weather.domain.model.ForecastData
import io.reactivex.Single

class ForecastCacheDataSourceImpl constructor(private val cache : WeatherCache<Any>) : ForecastLocalDataSource {

    override fun getFiveDayForecast(city: String, country: String): Single<ForecastData> = cache.load(city.toLowerCase().trim().plus("_").plus(country.toLowerCase().trim())) as Single<ForecastData>

    override fun setFiveDayForecast(city: String, country: String, data: ForecastData) {
        cache.write(city.toLowerCase().trim().plus("_").plus(country.toLowerCase().trim()), data)
    }

    override fun getCurrentWeather(city: String, country: String): Single<CurrentWeather> = cache.load("current_".plus(city.toLowerCase().trim().plus("_").plus(country.toLowerCase().trim()))) as Single<CurrentWeather>

    /**
     * Storing the data object in local cache with concatenated city and country values as unique key
     */
    override fun setCurrentWeather(city: String, country: String, data: CurrentWeather) {
        cache.write("current_".plus(city.toLowerCase().trim().plus("_").plus(country.toLowerCase().trim())), data)
    }
}