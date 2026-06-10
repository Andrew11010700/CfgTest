# Test Task

## Description

Android application that:

1. Loads products from remote API
2. Saves products to local Room database
3. Displays products list
4. Allows editing product name and description
5. Updates data in local database

## Architecture

MVVM

## Technologies

- Kotlin
- Coroutines
- Hilt
- Retrofit
- Room
- Navigation Component
- Data Binding
- RecyclerView

## Project Structure

- data
  - remote
  - local
  - repository
- ui
  - load
  - products
  - edit
- di

## Features

- Loading state handling
- Error handling
- Single source of truth (Room database)
- Navigation using product id
- Shared loader and message handling through BaseFragment

## API

https://fake-store-api.mock.beeceptor.com/api/products

## How to run

1. Clone repository
2. Open project in Android Studio
3. Sync Gradle
4. Run application
