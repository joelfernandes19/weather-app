package app.weather.datasource.model

import app.weather.domain.model.City
import app.weather.domain.model.Coordinates
import com.squareup.moshi.Json

/**
 * * Mapping City entity with domain
 */
data class CityEntity(
    @field:Json(name = "id") val id : Int,
    @field:Json(name = "name") val name : String,
    @field:Json(name = "country") val country : String,
    @field:Json(name = "coord") val coordinates : CoordinatesEntity
)

/**
 * * Mapping Coordinates entity with domain
 */
data class CoordinatesEntity(
    @field:Json(name = "lat") val lat : String,
    @field:Json(name = "lon") val lon : String
)

fun CoordinatesEntity.mapToDomain() : Coordinates = Coordinates(lat = lat, lon = lon)
fun CityEntity.mapToDomain(): City = City(id = id, name = name, country = country, coordinates = coordinates.mapToDomain())
