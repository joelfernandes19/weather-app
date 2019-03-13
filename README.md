# Weather App

A simple weather app Android project written in **Kotlin** with MVVM Architecture components. It uses **Koin** framework for dependency injection. 

Data is fetched from Open Weather Map - https://openweathermap.org/


## Running the project

1. Clone or download the project to your local system. 
2. Open the project with Android Studio 3.3 or later. 
3. Build project and Run

## TODO

- **Loading of data from local cache**
Once data has been loaded from remote data source, the same is currently being stored in cache. On next data retrieval call, we need to check if data exists in cache before making api call.
- **Auto detect current location**
