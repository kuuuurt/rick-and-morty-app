package com.kurt.example.rickandmorty.locations.presentation.locationslist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import com.kurt.example.rickandmorty.core.domain.usecases.GetAllLocations
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 07/31/2019
 */
class LocationsListViewModel(
    getAllLocations: GetAllLocations
) : ViewModel() {
    class Factory @Inject constructor(
        private val getAllLocations: GetAllLocations
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LocationsListViewModel::class.java)) {
                return LocationsListViewModel(getAllLocations) as T
            }
            throw IllegalArgumentException("ViewModel not found")
        }
    }

    val locationsDataSourceFactory = LocationsDataSource.Factory(getAllLocations, viewModelScope)
    val locations = LivePagedListBuilder(locationsDataSourceFactory, 20).build()
    val getLocationsState = Transformations.switchMap(locationsDataSourceFactory.sourceLiveData) {
        it.getLocationsState
    }
}