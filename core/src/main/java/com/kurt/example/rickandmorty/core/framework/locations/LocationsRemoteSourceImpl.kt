package com.kurt.example.rickandmorty.core.framework.locations

import com.kurt.example.rickandmorty.core.data.locations.LocationsRemoteSource
import com.kurt.example.rickandmorty.core.framework.BaseRemoteSource
import com.kurt.example.rickandmorty.core.framework.locations.remote.LocationsApi
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class LocationsRemoteSourceImpl @Inject constructor() : BaseRemoteSource<LocationsApi>(
    LocationsApi::class.java
), LocationsRemoteSource {
    override suspend fun getLocations() = api.getLocations().results
    override suspend fun getLocation(locationId: Int) = api.getLocation(locationId)
}