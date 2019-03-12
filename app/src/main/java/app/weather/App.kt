package app.weather

import android.app.Application
import app.weather.cache.CacheLibrary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin { androidContext(this@App) }

        CacheLibrary.init(this)
    }
}