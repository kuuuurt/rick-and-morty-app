package com.kurt.example.rickandmorty.locations.di

import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacter
import com.kurt.example.rickandmorty.core.domain.usecases.GetLocation
import com.kurt.example.rickandmorty.locations.presentation.locationdetails.LocationDetailsViewModel
import dagger.Module
import dagger.Provides

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
@Module
class LocationDetailsModule(private val locationId: Int) {
    @Provides
    fun providesLocationDetailsViewModelFactory(
        getLocation: GetLocation,
        getCharacter: GetCharacter
    ) = LocationDetailsViewModel.Factory(locationId, getLocation, getCharacter)

}