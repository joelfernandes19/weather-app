object ApplicationId {
    val id = "app.weather"
}

object Modules {
    val app = ":app"
    val network = ":network"
    val cache = ":cache"
}

object Releases {
    val versionCode = 2
    val versionName = "1.0.1"
}

object Versions {
    val gradle = "3.3.0"

    val compileSdk = 28
    val minSdk = 21
    val targetSdk = 28

    val googleServices = "4.2.0"

    val appcompat = "1.0.2"
    val design = "1.0.0"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val constraintlayout = "2.0.0-alpha3"

    val ktx = "1.0.0-alpha1"

    val kotlin = "1.3.21"
    val timber = "4.7.1"
    val rxjava = "2.2.5"
    val rxkotlin = "2.3.0"
    val retrofit = "2.5.0"
    val loggingInterceptor = "3.12.1"
    val paper = "2.6"
    val moshi = "1.8.0"
    val lifecycle = "2.0.0"
    val koin = "2.0.0-alpha-6"

    val junit = "4.12"
    val assertjCore = "3.11.1"
    val mockitoKotlin = "2.0.0-RC1"
    val mockitoInline = "2.23.4"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"

    val paper = "io.paperdb:paperdb:${Versions.paper}"
    val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val runner = "androidx.test:runner:1.1.1"
    val espresso = "androidx.test.espresso:espresso-core:3.1.1"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
    val powermock = "org.powermock:powermock-core:1.7.0RC2"
    val powermockModule = "org.powermock:powermock-module-junit4:1.7.0RC2"
    val powermockApi = "org.powermock:powermock-api-mockito2:1.7.0RC2"
}