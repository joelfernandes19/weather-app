package app.weather

import app.weather.cache.WeatherCache
import app.weather.data.datasource.ForecastLocalDataSource
import app.weather.data.datasource.ForecastRemoteDataSource
import app.weather.data.repository.ForecastRepositoryImpl
import app.weather.datasource.cache.ForecastCacheDataSourceImpl
import app.weather.datasource.model.ForecastDataEntity
import app.weather.datasource.remote.ForecastApi
import app.weather.datasource.remote.ForecastRemoteDataSourceImpl
import app.weather.domain.repository.WeatherRepository
import app.weather.domain.usecases.UCWeather
import app.weather.network.createNetworkClient
import app.weather.presentation.WeatherDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        viewModelModule,
        weatherUseCaseModule,
        weatherRepositoryModule,
        weatherDataSourceModule,
        networkModule,
        cacheModule
    )
}

val viewModelModule: Module = module {
    viewModel { WeatherDetailsViewModel(weatherUseCases = get()) }
}

val weatherUseCaseModule: Module = module {
    factory { UCWeather(weatherRepository = get()) }
}

val weatherRepositoryModule: Module = module {
    single { ForecastRepositoryImpl(remoteDataSource = get(), localDataSource = get()) as WeatherRepository }
}

val weatherDataSourceModule: Module = module {
    single { ForecastRemoteDataSourceImpl(apiRef = forecastApi) as ForecastRemoteDataSource }
    single { ForecastCacheDataSourceImpl(cache = get(FORECAST_CACHE)) as ForecastLocalDataSource }
}

val networkModule: Module = module {
    single { forecastApi }
}

val cacheModule: Module = module {
    single(name = FORECAST_CACHE) { WeatherCache<List<ForecastDataEntity>>() }
}


private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
private const val API_KEY = "c22887de190a6fc993c51236a2820bcb"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG, API_KEY)

private val forecastApi: ForecastApi = retrofit.create(ForecastApi::class.java)

private const val FORECAST_CACHE = "FORECAST_CACHE"