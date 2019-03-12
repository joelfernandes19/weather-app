package app.weather.datasource.remote

import app.weather.data.datasource.ForecastRemoteDataSource
import app.weather.datasource.model.mapToDomain
import app.weather.domain.model.CurrentWeather
import app.weather.domain.model.ForecastData
import app.weather.toQueryParam
import io.reactivex.Single

class ForecastRemoteDataSourceImpl constructor(private val apiRef : ForecastApi) : ForecastRemoteDataSource {

    override fun getFiveDayForecast(city: String, country: String): Single<ForecastData> =
        apiRef.getFiveDayForecast(toQueryParam(city, country)).map { it.mapToDomain() }

    override fun getCurrentWeather(city: String, country: String): Single<CurrentWeather> =
        apiRef.getCurrentWeather(toQueryParam(city, country)).map { it.mapToDomain() }
}