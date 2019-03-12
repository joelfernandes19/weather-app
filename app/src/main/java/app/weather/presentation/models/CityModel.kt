package app.weather.presentation.models

import app.weather.domain.model.City
import app.weather.domain.model.Coordinates

data class CityModel(
    val id : String,
    val name : String,
    val country : String,
    val coordinates : CoordinatesModel
)

data class CoordinatesModel(
    val lat : String,
    val lon : String
)


fun Coordinates.mapToPresentation() : CoordinatesModel =
    CoordinatesModel(lat = lat, lon = lon)
fun City.mapToPresentation(): CityModel = CityModel(
    id = id,
    name = name,
    country = country,
    coordinates = coordinates.mapToPresentation()
)
