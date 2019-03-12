package app.weather.presentation.models

import app.weather.domain.model.*
import app.weather.formatDate
import app.weather.formatTemperature

data class CurrentWeatherModel(
    val dateUTC : Long,
    val weatherConditions : ConditionsModel,
    val weatherInfo : List<WeatherInfoModel>,
    val clouds : CloudsModel,
    val wind : WindModel,
    val rain : RainModel,
    val dateFormatted : String,
    val id : String,
    val name : String,
    val coordinates : CoordinatesModel
)
fun CurrentWeather.mapToPresentation() : CurrentWeatherModel =
    CurrentWeatherModel(
        dateUTC = dateUTC,
        weatherConditions = weatherConditions.mapToPresentation(),
        weatherInfo = weatherInfoList.mapListToPresentation(),
        clouds = clouds.mapToPresentation(),
        wind = wind.mapToPresentation(),
        rain = rain.mapToPresentation(),
        dateFormatted = formatDate(dateUTC),
        id = id,
        name = name,
        coordinates = coordinates.mapToPresentation()
    )


data class ForecastDayWiseModel(val hourlyList : ArrayList<List<ForecastModel>> = ArrayList(), val cityInfo : CityModel)

data class ForecastDataModel(val list : List<ForecastModel>, val cityInfo : CityModel)

fun ForecastData.mapToPresentation() : ForecastDataModel =
    ForecastDataModel(
        list = list.mapToPresentation(),
        cityInfo = cityInfo.mapToPresentation()
    )

data class ForecastModel(
    val dateUTC : Long,
    val weatherConditions : ConditionsModel,
    val weatherInfo : List<WeatherInfoModel>,
    val clouds : CloudsModel,
    val wind : WindModel,
    val rain : RainModel,
    val dateTime : String,
    val dateFormatted : String
)

fun List<Forecast>.mapToPresentation() : List<ForecastModel> = map { it.mapToPresentation() }
fun Forecast.mapToPresentation() : ForecastModel =
    ForecastModel(
        dateUTC = dateUTC,
        weatherConditions = weatherConditions.mapToPresentation(), weatherInfo = weatherInfo.mapListToPresentation(),
        clouds = clouds.mapToPresentation(), wind = wind.mapToPresentation(), rain = rain.mapToPresentation(),
        dateTime = dateTime, dateFormatted = formatDate(dateUTC)
    )
fun List<WeatherInfo>.mapListToPresentation() : List<WeatherInfoModel> = map { it.mapToPresentation() }

data class ConditionsModel(
    val temp : Double,
    val minTemp : Double,
    val maxTemp : Double,
    val maxMinTempFormatted : String,
    val pressure : Double,
    val seaLevel : Double,
    val groundLevel : Double,
    val humidity : Double
)
fun Conditions.mapToPresentation() : ConditionsModel =
    ConditionsModel(
        temp = temp, minTemp = minTemp, maxTemp = maxTemp,
        maxMinTempFormatted = "Min ${formatTemperature(minTemp)}\nMax ${formatTemperature(maxTemp)}",
        pressure = pressure, seaLevel = seaLevel, groundLevel = groundLevel, humidity = humidity
    )

data class WeatherInfoModel(
    val id : String,
    val params : String,
    val description : String,
    val icon : String
)
fun WeatherInfo.mapToPresentation() : WeatherInfoModel =
    WeatherInfoModel(id = id, params = params, description = description, icon = icon)

data class CloudsModel(
    val cloudiness : Int
)
fun Clouds.mapToPresentation() : CloudsModel =
    CloudsModel(cloudiness = cloudiness)


data class WindModel(
    val speed : Double,
    val directionDegrees : Double
)
fun Wind.mapToPresentation() : WindModel =
    WindModel(speed = speed, directionDegrees = directionDegrees)

data class RainModel(val threeHourRainVolume : Double)
fun Rain.mapToPresentation() : RainModel =
    RainModel(threeHourRainVolume = threeHourRainVolume)