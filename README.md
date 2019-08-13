# Rick and Morty App
An app created to try out the Navigation Architecture Component


## Libraries and Tools Used

- Kotlin
- AndroidX
- Material Components
- Android Architecture Components
    - Lifecycle and ViewModel
    - Paging
    - Navigation
        - SafeArgs
- Retrofit
- Moshi
- Kotlin Coroutines
- Glide
- Dagger 2

## Module Structure

The project is modularized in the following way.

**app** contains the entry-point Activity for the app. It contains the MainActivity which houses the BottomNavigationView for navigating between the features

**core** contains shared code throughout the app like models, use-cases, and base classes

**characters**, **locations**, **episodes** contains the features for each respective model such as viewing the list of objects and viewing an object's details


## Project Structure

This project is built using Clean Architecture and is structured in the following way:

**presentation** - contains Activities/Fragments and their corresponding ViewModels and Adapters for the presentation layer

**domain** - contains entities and use cases for the **presentation** layer to access data from the **data** layer

**data** -  contains data models and repositories for getting data

**framework** - contains implementation details for network and db layer (Retrofit/Room/SQLDelight/Realm)

**di** - contains classes for dependency injection. In this case, Dagger's components and modules.


## TODO

- Navigation
- SQLDelight
- Unit Testing

## Authors

- Kurt Renzo Acosta - [kurt.r.acosta@gmail.com](mailto:kurt.r.acosta@gmail.com)

## License


    Copyright 2019 Kurt Renzo Acosta

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at


        http://www.apache.org/licenses/LICENSE-2.0


    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.