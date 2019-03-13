package app.weather.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.weather.currentWeather
import app.weather.domain.usecases.UCWeather
import app.weather.forecastData
import app.weather.presentation.models.mapToPresentation
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.*


private const val CITY = "Bangalore"
private const val COUNTRY = "IN"

//@RunWith(PowerMockRunner::class)
//@PrepareForTest(DateFormat::class)
class WeatherDetailsVideoModelTest {

    private lateinit var vm : WeatherDetailsViewModel
    private val mockWeatherUseCase : UCWeather = mock()
    private val cal : Calendar = mock()

    private val throwable = Throwable()

    @Rule
    @JvmField val rxSchedulersOverrideRule = RxSchedulersOverrideRule()

    @Rule
    @JvmField val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val forecastDataMock = forecastData
    private val currentWeatherMock = currentWeather

    @Before
    fun init() {
        vm = WeatherDetailsViewModel(mockWeatherUseCase)
        //PowerMockito.mockStatic(DateFormat::class.java)
        //PowerMockito.`when`(DateFormat.format("MMMM d", cal)).thenReturn(StringBuffer("March 12"))
    }

    @Test
    fun getFiveDayForecastSuccess() {

        whenever(mockWeatherUseCase.getFiveDaysForecast(CITY, COUNTRY, false))
            .thenReturn(Single.just(forecastDataMock))

        vm.getFiveDayForecast(CITY, COUNTRY, false)

        verify(mockWeatherUseCase).getFiveDaysForecast(CITY, COUNTRY, false)
        Assert.assertEquals(forecastDataMock.mapToPresentation(), vm.forecastData.value)
    }

    @Test
    fun getFiveDayForecastFail() {
        whenever(mockWeatherUseCase.getFiveDaysForecast(CITY, COUNTRY, false)).thenReturn(Single.error(throwable))

        vm.getFiveDayForecast(CITY, COUNTRY, false)

        verify(mockWeatherUseCase).getFiveDaysForecast(CITY, COUNTRY, false)
        Assert.assertEquals(null, vm.forecastData.value)
    }


    @Test
    fun getCurrentWeatherSuccess() {

        whenever(mockWeatherUseCase.getCurrentWeather(CITY, COUNTRY, false))
            .thenReturn(Single.just(currentWeatherMock))

        vm.getCurrentWeather(CITY, COUNTRY, false)

        verify(mockWeatherUseCase).getCurrentWeather(CITY, COUNTRY, false)
        Assert.assertEquals(currentWeatherMock.mapToPresentation(), vm.currentWeatherData.value)
    }

    @Test
    fun getCurrentWeatherFail() {
        whenever(mockWeatherUseCase.getCurrentWeather(CITY, COUNTRY, false)).thenReturn(Single.error(throwable))

        vm.getCurrentWeather(CITY, COUNTRY, false)

        verify(mockWeatherUseCase).getCurrentWeather(CITY, COUNTRY, false)
        Assert.assertEquals(null, vm.currentWeatherData.value)
    }
}