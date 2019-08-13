package com.kurt.example.rickandmorty.core.data.locations

import com.kurt.example.rickandmorty.core.domain.repositories.LocationsRepository
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class LocationsRepositoryImpl @Inject constructor(
    private val remoteSource: LocationsRemoteSource
) : LocationsRepository {
    override suspend fun getLocations(page: Int?) = remoteSource.getLocations(page)
    override suspend fun getLocation(locationId: Int) = remoteSource.getLocation(locationId)
}