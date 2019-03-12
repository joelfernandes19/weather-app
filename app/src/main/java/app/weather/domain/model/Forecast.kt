package app.weather.domain.model


data class CurrentWeather(
    val dateUTC : Long,
    val weatherConditions : Conditions,
    val weatherInfoList : List<WeatherInfo>,
    val clouds : Clouds,
    val wind : Wind,
    val rain : Rain,
    val id : String,
    val name : String,
    val coordinates : Coordinates
)

data class ForecastData(
    val list : List<Forecast>,
    val cityInfo : City
)

data class Forecast(
    val dateUTC : Long,
    val weatherConditions : Conditions,
    val weatherInfo : List<WeatherInfo>,
    val clouds : Clouds,
    val wind : Wind,
    val rain : Rain,
    val dateTime : String
)

data class Conditions(
    val temp : Double,
    val minTemp : Double,
    val maxTemp : Double,
    val pressure : Double,
    val seaLevel : Double,
    val groundLevel : Double,
    val humidity : Double
)

data class WeatherInfo(
    val id : String,
    val params : String,
    val description : String,
    val icon : String
)

data class Clouds(
    val cloudiness : Int
)
data class Wind(
    val speed : Double,
    val directionDegrees : Double
)

data class Rain(val threeHourRainVolume : Double)