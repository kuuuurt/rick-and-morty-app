package com.kurt.example.rickandmorty.core.domain.repositories

import com.kurt.example.rickandmorty.core.domain.entities.Location

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
interface LocationsRepository {
    suspend fun getAllLocations(page: Int? = null): List<Location>
    suspend fun getLocation(locationId: Int): Location
}