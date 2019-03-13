package app.weather.presentation

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import app.weather.*
import app.weather.presentation.adapters.ForecastAdapter
import app.weather.presentation.models.CurrentWeatherModel
import app.weather.presentation.models.ForecastDayWiseModel
import app.weather.presentation.models.ForecastModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_forecast_layout.*
import org.koin.androidx.viewmodel.ext.viewModel
import timber.log.Timber
import java.util.*


class MainActivity : AppCompatActivity() {

    private var checkedItem = 0
    lateinit var dialog: AlertDialog
    private val vm: WeatherDetailsViewModel by viewModel()

    private val forecastList = ArrayList<List<ForecastModel>>()
    private lateinit var forecastAdapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(app.weather.R.layout.activity_main)

        /**
         * Using Koin - a dependency injection framework that is written in Kotlin
         * https://insert-koin.io/
         */
        injectModules()

        vm.forecastData.observe(this, Observer {
            updateForecast(it)
        })

        vm.currentWeatherData.observe(this, Observer {
            updateCurrentWeather(it)
        })


        forecastAdapter = ForecastAdapter(this, forecastList)
        rvForecast.isNestedScrollingEnabled = false
        rvForecast.adapter = forecastAdapter

        /**
         * When offline, data will loaded from cache if available
         */
        vm.getCurrentWeatherAndForecast("London", "UK", isConnected)

        tvCity.setOnClickListener {
            showCitiesList()
        }
    }

    private fun updateCurrentWeather(currentWeatherModel: CurrentWeatherModel) {
        tvCurrentTemp.text = formatTemperature(currentWeatherModel.weatherConditions.temp)
        tvLocalTime.text = currentWeatherModel.dateUTC.formatDate()
        tvLastSynced.text = "Last updated: ${formatDateWithTime(currentWeatherModel.dateUTC)}"
    }


    private fun updateForecast(forecastData : ForecastDayWiseModel) {
        Timber.tag("Forecast").i("Forecast data list size : ${forecastData.hourlyList.size}")
        if(forecastData.hourlyList.isNotEmpty()) {
            forecastList.clear()
            forecastList.addAll(forecastData.hourlyList)
            forecastAdapter.notifyDataSetChanged()
        }

        tvCity.text = forecastData.cityInfo.name.plus(", ${forecastData.cityInfo.country}")
    }


    /**
     * A basic alert dialog to show a list of cities with a single choice item
     * With this, the user can switch between cities to get weather and forecase
     */
    private fun showCitiesList() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select a city")

        val cities = arrayOf("London, GB", "Bangalore, IN", "New York, US")

        builder.setSingleChoiceItems(cities, checkedItem, DialogInterface.OnClickListener { dialog, which ->
            checkedItem = which
            val location = cities[which].split(", ")
            vm.getCurrentWeatherAndForecast(location[0], location[1], isConnected)
            dialog.dismiss()
        })

        dialog = builder.create()
        dialog.show()
    }
}
