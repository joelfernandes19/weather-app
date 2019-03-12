package app.weather.datasource.remote

import app.weather.datasource.model.CurrentWeatherEntity
import app.weather.datasource.model.ForecastDataEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast/")
    fun getFiveDayForecast(
        @Query("q") query : String, @Query("units") unit : String="metric"): Single<ForecastDataEntity>

    @GET("weather/")
    fun getCurrentWeather(@Query("q") query : String, @Query("units") unit : String="metric"): Single<CurrentWeatherEntity>
}