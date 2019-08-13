package com.kurt.example.rickandmorty.core.framework.locations.remote

import com.kurt.example.rickandmorty.core.domain.entities.Location
import com.kurt.example.rickandmorty.core.framework.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
interface LocationsApi {
    @GET("location/")
    suspend fun getLocations(@Query("page") page: Int?): RickAndMortyResponse<Location>

    @GET("location/{locationId}")
    suspend fun getLocation(@Path("locationId") locationId: Int): Location
}