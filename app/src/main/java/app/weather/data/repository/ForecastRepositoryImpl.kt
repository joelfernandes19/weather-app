package app.weather.data.repository

import app.weather.data.datasource.ForecastLocalDataSource
import app.weather.data.datasource.ForecastRemoteDataSource
import app.weather.domain.model.CurrentWeather
import app.weather.domain.model.ForecastData
import app.weather.domain.repository.WeatherRepository
import io.reactivex.Single

class ForecastRepositoryImpl constructor(private val remoteDataSource : ForecastRemoteDataSource,
                                         private val localDataSource : ForecastLocalDataSource) : WeatherRepository {

    /**
     * TODO: Retrieving data from cache based on device connectivity.
     *
     * Need to check if there is cached data for the give city, country, and then retrieve the data
     * If local data is available, we send it back to the called. But at the same time, we continue with our remote api call
     * This will ensure that the cache is updated with latest data. Once we have the latest data from the API, we send it back again to the caller
     *
     *
     * TODO: Writing test cases fo the same.
     */

    override fun getFiveDayForecast(city: String, country: String, refresh : Boolean) : Single<ForecastData> =

        when(refresh) {
            true -> {
                remoteDataSource.getFiveDayForecast(city, country).doOnSuccess {
                    localDataSource.setFiveDayForecast(city, country, it)
                }
            }
            false -> {
                localDataSource.getFiveDayForecast(city, country)
            }
        }

    override fun getCurrentWeather(city: String, country: String, refresh: Boolean): Single<CurrentWeather> =
        when(refresh) {
            true -> {
                remoteDataSource.getCurrentWeather(city, country).doOnSuccess {
                    localDataSource.setCurrentWeather(city, country, it)
                }
            }
            false -> {
                localDataSource.getCurrentWeather(city, country)
            }
        }
}