package com.kurt.example.rickandmorty.core.di

import com.kurt.example.rickandmorty.core.data.locations.LocationsRemoteSource
import com.kurt.example.rickandmorty.core.data.locations.LocationsRepositoryImpl
import com.kurt.example.rickandmorty.core.domain.repositories.LocationsRepository
import com.kurt.example.rickandmorty.core.framework.locations.LocationsRemoteSourceImpl
import dagger.Binds
import dagger.Module

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
@Module
abstract class LocationsModule {
    @Binds
    abstract fun provideLocationsRepository(locationsRepositoryImpl: LocationsRepositoryImpl): LocationsRepository

    @Binds
    abstract fun provideLocationsRemoteSource(locationsRemoteSourceImpl: LocationsRemoteSourceImpl): LocationsRemoteSource
}