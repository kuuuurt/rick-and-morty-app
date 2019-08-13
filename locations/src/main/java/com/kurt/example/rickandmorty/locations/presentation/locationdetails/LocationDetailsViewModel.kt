package com.kurt.example.rickandmorty.locations.presentation.locationdetails

import androidx.lifecycle.*
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.domain.entities.Location
import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacter
import com.kurt.example.rickandmorty.core.domain.usecases.GetLocation
import com.kurt.example.rickandmorty.core.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class LocationDetailsViewModel(
    private val locationId: Int,
    private val getLocation: GetLocation,
    private val getCharacter: GetCharacter
) : ViewModel() {
    class Factory(
        private val locationId: Int,
        private val getLocation: GetLocation,
        private val getCharacter: GetCharacter
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LocationDetailsViewModel::class.java)) {
                return LocationDetailsViewModel(locationId, getLocation, getCharacter) as T
            }
            throw IllegalArgumentException("ViewModel not found.")
        }
    }

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    private val _getLocationState = MutableLiveData<UiState>()
    val getLocationState: LiveData<UiState> = _getLocationState

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _getCharactersState = MutableLiveData<UiState>()
    val getCharactersState: LiveData<UiState> = _getCharactersState

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _getLocationState.postValue(UiState.Error(throwable))
        }) {
            _getLocationState.postValue(UiState.Loading)
            val location = getLocation(locationId)
            _location.postValue(location)
            _getLocationState.postValue(UiState.Complete)

            launch(CoroutineExceptionHandler { _, throwable ->
                _getCharactersState.postValue(UiState.Error(throwable))
            }) {
                _getCharactersState.postValue(UiState.Loading)
                val deferredCharacters = location.residents
                    .map { it.split("/").last().toInt() }
                    .map { async { getCharacter(it) } }

                val characters = deferredCharacters.awaitAll()
                _characters.postValue(characters)
                _getCharactersState.postValue(UiState.Complete)
            }

        }
    }
}