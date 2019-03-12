package app.weather

import app.weather.domain.model.*

private val weatherInfo = WeatherInfo(id = 800, params = "Clear", description = "clear sky", icon = "01n")
private val conditions = Conditions(temp = 25.41, maxTemp = 25.41, minTemp = 23.28, pressure = 1013.84, seaLevel = 1013.84, groundLevel = 918.34, humidity = 42.0)
private val rain = Rain(threeHourRainVolume = 0.0)
private val wind = Wind(speed = 5.22, directionDegrees = 126.001)
private val clouds = Clouds(cloudiness = 0)

private val forecast = Forecast(dateTime =  "2019-03-12 18:00:00", dateUTC = 1552413600,
    weatherConditions = conditions,rain = rain, wind = wind, clouds = clouds, weatherInfo = arrayListOf(weatherInfo))


val coordinates = Coordinates(lat = "12.9762", lon = "77.6033")
val city = City(id = 1277333, name = "Bangalore", coordinates = coordinates, country = "IN")


val forecastData = ForecastData(list = arrayListOf(forecast), cityInfo = city)

val currentWeather = CurrentWeather(
    dateUTC = 1552413600,
    name = "Bangalore",
    id = 127333,
    weatherConditions = conditions,rain = rain, wind = wind, clouds = clouds, coordinates = coordinates, weatherInfoList = arrayListOf(weatherInfo)
)