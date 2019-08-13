package com.kurt.example.rickandmorty.core.data.locations

import com.kurt.example.rickandmorty.core.domain.entities.Location

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
interface LocationsRemoteSource {
    suspend fun getLocations(page: Int?): List<Location>
    suspend fun getLocation(locationId: Int): Location
}