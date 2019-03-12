package app.weather.datasource.model

import app.weather.domain.model.*
import com.squareup.moshi.Json

data class CurrentWeatherEntity(
    @field:Json(name = "dt") val dateUTC : Long,
    @field:Json(name = "main") val weatherConditions : ConditionsEntity,
    @field:Json(name = "weather") val weatherInfoList : List<WeatherInfoEntity>,
    @field:Json(name = "clouds") val clouds : CloudsEntity,
    @field:Json(name = "wind") val wind : WindEntity,
    @field:Json(name = "rain") val rain : RainEntity?,
    @field:Json(name = "id") val id : String,
    @field:Json(name = "name") val name : String,
    @field:Json(name = "coord") val coordinates : CoordinatesEntity
)
fun CurrentWeatherEntity.mapToDomain() : CurrentWeather = CurrentWeather(
    dateUTC = dateUTC,
    weatherConditions = weatherConditions.mapToDomain(),
    weatherInfoList = weatherInfoList.mapListToDomain(),
    clouds = clouds.mapToDomain(),
    wind = wind.mapToDomain(),
    rain = if(rain == null) RainEntity().mapToDomain() else rain.mapToDomain(),
    id = id, name = name, coordinates = coordinates.mapToDomain())


data class ForecastDataEntity(
    @field:Json(name = "list") val list : List<ForecastEntity>,
    @field:Json(name = "city") val cityInfo : CityEntity
)

fun ForecastDataEntity.mapToDomain() : ForecastData = ForecastData(list = list.mapToDomain(), cityInfo = cityInfo.mapToDomain())

data class ForecastEntity(
    @field:Json(name = "dt") val dateUTC : Long,
    @field:Json(name = "main") val weatherConditions : ConditionsEntity,
    @field:Json(name = "weather") val weatherInfoList : List<WeatherInfoEntity>,
    @field:Json(name = "clouds") val clouds : CloudsEntity,
    @field:Json(name = "wind") val wind : WindEntity,
    @field:Json(name = "rain") val rain : RainEntity?,
    @field:Json(name = "dt_txt") val dateTime : String
)
fun List<ForecastEntity>.mapToDomain() : List<Forecast> = map { it.mapToDomain() }
fun ForecastEntity.mapToDomain() : Forecast = Forecast(dateUTC = dateUTC,
    weatherConditions = weatherConditions.mapToDomain(),
    weatherInfo = weatherInfoList.mapListToDomain(),
    clouds = clouds.mapToDomain(),
    wind = wind.mapToDomain(),
    rain = if(rain == null) RainEntity().mapToDomain() else rain.mapToDomain(),
    dateTime = dateTime)
fun List<WeatherInfoEntity>.mapListToDomain() : List<WeatherInfo> = map { it.mapToDomain() }


/**
 * Weather conditions entity
 *
 */
data class ConditionsEntity(
    @field:Json(name = "temp") val temp : Double,
    @field:Json(name = "temp_min") val minTemp : Double,
    @field:Json(name = "temp_max") val maxTemp : Double,
    @field:Json(name = "pressure") val pressure : Double,
    @field:Json(name = "sea_level") val seaLevel : Double,
    @field:Json(name = "grnd_level") val groundLevel : Double,
    @field:Json(name = "humidity") val humidity : Double
)

/**
 * Mapping weather conditions info to domain model
 *
 */
fun ConditionsEntity.mapToDomain() : Conditions = Conditions(temp = temp, minTemp = minTemp, maxTemp = maxTemp,
    pressure = pressure, seaLevel = seaLevel, groundLevel = groundLevel, humidity = humidity)

/**
 * More info Weather condition codes
 *
 */
data class WeatherInfoEntity(
    @field:Json(name = "id") val id : String,
    @field:Json(name = "main") val params : String,
    @field:Json(name = "description") val description : String,
    @field:Json(name = "icon") val icon : String
)

/**
 * Mapping info to domain model
 *
 */
fun WeatherInfoEntity.mapToDomain() : WeatherInfo = WeatherInfo(id = id, params = params, description = description, icon = icon)

/**
 * Cloudiness in %
 *
 */
data class CloudsEntity(
    @field:Json(name = "all") val cloudiness : Int
)

/**
 * Mapping % to domain model
 *
 */
fun CloudsEntity.mapToDomain() : Clouds = Clouds(cloudiness = cloudiness)


/**
 * Wind entity with wind speed and wind direction in degrees
 * @field: speed : Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
 * @field: deg : Wind direction, degrees (meteorological)
 */
data class WindEntity(
    @field:Json(name = "speed") val speed : Double,
    @field:Json(name = "deg") val directionDegrees : Double
)
fun WindEntity.mapToDomain() : Wind = Wind(speed = speed, directionDegrees = directionDegrees)

data class RainEntity(@field:Json(name = "3h") val threeHourRainVolume : Double = 0.0)
fun RainEntity.mapToDomain() : Rain = Rain(threeHourRainVolume = threeHourRainVolume)