# OBilet Android Assignment

## Features

|                        Splash                         |                                    Bus Section                                    |                                     Flight Section                                      |
|:-----------------------------------------------------:|:---------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------:|
| <img src="gifs/splash.gif" alt="splash" width="250"/> | <img src="gifs/bus_section_fragment.gif" alt="bus_section_fragment" width="250"/> | <img src="gifs/flight_section_fragment.gif" alt="flight_section_fragment" width="250"/> |

|                                                                           Locations                                                                           |                                  Journeys                                   |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------:|
| <img src="gifs/locations_fragment1.gif" alt="locations_fragment" width="250"/> <img src="gifs/locations_fragment2.gif" alt="locations_fragment" width="250"/> | <img src="gifs/journeys_fragment.gif" alt="journeys_fragment" width="250"/> |

|                                 Passenger Filter                                 |
|:--------------------------------------------------------------------------------:|
| <img src="gifs/passenger_filter_dialog.gif" alt="passenger_filter" width="250"/> |

## Tech Features & Libraries

* MVVM Architecture
* XML with Jetpack Compose
* Multi-module
* Kotlin Gradle DSL
* Kotlin Coroutines & Flow
* LiveData

### Libraries
* [Jetpack Compose](https://developer.android.com/develop/ui/compose/setup)
* [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
* [Retrofit](https://square.github.io/retrofit/)
* [Moshi](https://github.com/square/moshi)
* [okhttp](https://github.com/square/okhttp)
* [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
* [Truth](https://truth.dev/)
* [Room](https://developer.android.com/training/data-storage/room)
* [Timber](https://github.com/JakeWharton/timber)
* [Coil](https://coil-kt.github.io/coil/)
* [Dagger Hilt](https://dagger.dev/hilt/)

## Module Structure
| Module name                    | Type                | Description                                                                                                            |
|--------------------------------|---------------------|------------------------------------------------------------------------------------------------------------------------|
| [app](/app/)                   | Android Application | Contains all features and binds all of the modules.                                                                    |
| [core-network](/core-network/) | Android Library     | This module contains network related classes such as services, remote data sources, endpoints.                         |
| [core-cache](/core-cache/)     | Android Library     | This module contains cache related classes such as room dao, entity models & cache data sources.                       |
| [core-data](/core-data/)       | Android Library     | This module contains data related classes such as repositories. Also this module connects remote & cache data sources. |
| [core-model](/core-model/)     | Android Library     | This module contains model classes that will be used on the presentation layer.                                        |
| [core-storage](/core-storage/) | Android Library     | This module contains storage related classes.                                                                          |

<img src="assets/modules.png" alt="modules" width="850"/>


