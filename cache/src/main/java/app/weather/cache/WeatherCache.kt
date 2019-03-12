package app.weather.cache

import android.content.Context
import io.paperdb.Book
import io.paperdb.Paper
import io.reactivex.Single

/**
 * Paper is a fast NoSQL-like storage for Java/Kotlin objects
 */

object CacheLibrary {
    fun init(context: Context) {
        Paper.init(context)
    }
}

class WeatherCache<T> {

    private val book: Book = Paper.book()

    fun load(key: String): Single<T> {
        return Single.create { emitter ->
            if(exists(key))
                emitter.onSuccess(book.read(key))
            else
                emitter.onError(Throwable("No data found for key = $key"))
        }
    }

    fun write(key : String, any: T) {
        book.write(key, any)
    }

    fun delete(key : String) {
        book.delete(key)
    }

    private fun exists(key : String) : Boolean = book.contains(key)
}
