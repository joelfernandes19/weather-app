package app.weather

import android.content.Context
import android.content.res.Resources
import android.net.ConnectivityManager
import android.text.format.DateFormat
import java.util.*

fun toQueryParam(city : String, country : String) : String {
    return city.plus(",").plus(country)
}

val Context.isConnected: Boolean
    get() {
        return (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo?.isConnected == true
    }

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun formatDate(timestamp : Long) : String {
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = timestamp * 1000L
    return DateFormat.format("MMMM d", cal).toString()
}

fun formatDateWithTime(timestamp : Long) : String {
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = timestamp * 1000L
    return DateFormat.format("MMMM d, HH:mm", cal).toString()
}

fun getTimeInHourMin(timestamp : Long) : String {
    val cal = Calendar.getInstance(Locale.ENGLISH)
    cal.timeInMillis = timestamp * 1000L
    return DateFormat.format("h:mm a", cal).toString()
}

fun formatTemperature(temperature: Double): String {
    val suffix = "\u00B0" //For now showing only in C
    val roundedString = String.format("%.1f",temperature)
    return "$roundedString ${suffix}C"
}