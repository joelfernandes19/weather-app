package app.weather.presentation

import android.text.format.DateUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.weather.domain.usecases.UCWeather
import app.weather.presentation.models.CurrentWeatherModel
import app.weather.presentation.models.ForecastDayWiseModel
import app.weather.presentation.models.ForecastModel
import app.weather.presentation.models.mapToPresentation
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WeatherDetailsViewModel constructor(private val weatherUseCases: UCWeather) : ViewModel() {

    val forecastData = MutableLiveData<ForecastDayWiseModel>()
    val currentWeatherData = MutableLiveData<CurrentWeatherModel>()
    private val compositeDisposable = CompositeDisposable()

    fun getCurrentWeatherAndForecast(city : String, country : String, refresh: Boolean) {
        getCurrentWeather(city, country, refresh)
        getFiveDayForecast(city, country, refresh)
    }

    private fun getFiveDayForecast(city : String, country : String, refresh: Boolean) =
        compositeDisposable.add(weatherUseCases.getFiveDaysForecast(city, country, refresh)
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({

                val tempList = ArrayList<ForecastModel>()
                val otherDaysList = ArrayList<ForecastModel>()
                val dayWiseModel = ForecastDayWiseModel(cityInfo = it.cityInfo)

                Timber.plant(Timber.DebugTree())
                Timber.tag("WeatherDetailsViewModel")

                for ( forecast in it.list) {
                    if (DateUtils.isToday(forecast.dateUTC * 1000)) {
                        tempList.add(forecast)
                    } else {
                        otherDaysList.add(forecast)
                    }
                }
                dayWiseModel.hourlyList.add(ArrayList(tempList))

                var tempDate = ""
                tempList.clear()
                for(forecast in otherDaysList) {
                    if(tempDate.isEmpty())
                        tempDate = forecast.dateFormatted

                    if(tempDate.equals(forecast.dateFormatted)) {
                        tempList.add(forecast)
                    } else {
                        dayWiseModel.hourlyList.add(ArrayList(tempList))
                        tempList.clear()
                        tempDate = forecast.dateFormatted
                        tempList.add(forecast)
                    }
                }

                forecastData.postValue(dayWiseModel)
            }, {
                it.printStackTrace()
            })
        )


    private fun getCurrentWeather(city: String, country: String, refresh: Boolean) =
        compositeDisposable.add(weatherUseCases.getCurrentWeather(city, country, refresh)
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({
                currentWeatherData.postValue(it)
            }, {
                it.printStackTrace()
            })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}
