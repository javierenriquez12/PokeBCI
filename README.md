
<p align="center">
Pokedex is a project that contains a list of pokemons and its details where the latest android technologies are included</p>

[![Android](https://badgen.net/badge/documentation/v1.0.1/green)]()

<!-- region:description -->

This project aims to meet the challenge of BCI, in addition to having the implementation of MVVM + Clean Architecture + Dagger hilt + Flow, and android jetpack libraries

<!-- endregion:description -->

## Table of Contents

1. [let's start](#let's start)
2. [Quick Start](#quick-start)
3. [Use and demonstration](#use-and-demonstration)
4. [Compatibility](#compatibility)

<a name="let's start"></a>

## Let us begin

<a name="quick-start"></a>

## Quick start

[//]: # 'region:quick-start'

> No additional configuration required

[//]: # 'endregion:quick-start'

<a name="use-and-demonstration"></a>

## Usage and demo

[//]: # 'region:usage-demo'

[//]: # 'general content diagram'

#### Internal structure
The project included Modular Architecture was used, for organized a codebase into loosely coupled and self contained parts. Each module is independent and serves a clear purpose. By dividing a problem into smaller and easier to solve subproblems, you reduce the complexity of designing and maintaining a large system.

<p align="center">
    <img src="images/pokemon_architecture.png" />
</p>

Also, included MVVM (Model View ViewModel) architecture pattern was used, coroutines, live data, sealed classes, repository and data sources.

> Navigation should be handled via [**Navigation Component**] (https://developer.android.com/guide/navigation/navigation-getting-started).<br><br>
> In case other activities have to be managed, the stack of views must be taken into account to be able to remove them when the project flow ends.

<p align="center">
    <img src="images/pokemon_clean_module.png" />
</p>

If the project starts to grow, it is recommended to use a clean architecture as shown below:




<a name="compatibility"></a>

## Compatibilidad

| Min SDK              | Target SDK          | Java     | Kotlin | AGP  |
|----------------------|---------------------|----------|--------|------|
| API 21 (Android 5.0) | API 33 (Android 13) | Java 11+ | 1.7.0  | 7.0+ |