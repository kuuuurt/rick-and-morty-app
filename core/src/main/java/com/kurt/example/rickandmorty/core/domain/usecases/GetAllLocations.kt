package com.kurt.example.rickandmorty.core.domain.usecases

import com.kurt.example.rickandmorty.core.domain.repositories.LocationsRepository
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class GetAllLocations @Inject constructor(private val locationsRepository: LocationsRepository) {
    suspend operator fun invoke(page: Int? = null) = locationsRepository.getAllLocations(page)
}