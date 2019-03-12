package app.weather.domain.model

data class City(
    val id : String,
    val name : String,
    val country : String,
    val coordinates : Coordinates
)

data class Coordinates(
    val lat : String,
    val lon : String
)