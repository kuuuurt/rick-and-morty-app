package com.kurt.example.rickandmorty.locations.presentation.locationslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.kurt.example.rickandmorty.core.domain.entities.Location
import com.kurt.example.rickandmorty.core.domain.usecases.GetAllLocations
import com.kurt.example.rickandmorty.core.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class LocationsDataSource(
    private val getAllLocations: GetAllLocations,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Location>() {

    class Factory(
        private val getAllLocations: GetAllLocations,
        private val scope: CoroutineScope
    ) : DataSource.Factory<Int, Location>() {
        val sourceLiveData = MutableLiveData<LocationsDataSource>()
        private lateinit var latestSource: LocationsDataSource

        override fun create(): DataSource<Int, Location> {
            latestSource = LocationsDataSource(getAllLocations, scope)
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }

    private val _getLocationsState = MutableLiveData<UiState>()
    val getLocationsState: LiveData<UiState> = _getLocationsState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Location>
    ) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getLocationsState.postValue(UiState.Error(throwable))
        }) {
            _getLocationsState.postValue(UiState.Loading)
            val locations = getAllLocations()
            callback.onResult(locations, null, 2)
            _getLocationsState.postValue(UiState.Complete)
            if (locations.isEmpty()) {
                _getLocationsState.postValue(UiState.Empty)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Location>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getLocationsState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(getAllLocations(params.key), params.key + 1)
            _getLocationsState.postValue(UiState.Complete)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Location>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getLocationsState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(getAllLocations(params.key), params.key - 1)
            _getLocationsState.postValue(UiState.Complete)
        }
    }
}