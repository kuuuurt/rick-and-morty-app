package com.kurt.example.rickandmorty.locations.presentation.locationdetails

import androidx.lifecycle.*
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.domain.entities.Location
import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacter
import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacters
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
    private val getCharacters: GetCharacters
) : ViewModel() {
    class Factory(
        private val locationId: Int,
        private val getLocation: GetLocation,
        private val getCharacters: GetCharacters
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LocationDetailsViewModel::class.java)) {
                return LocationDetailsViewModel(locationId, getLocation, getCharacters) as T
            }
            throw IllegalArgumentException("ViewModel not found.")
        }
    }

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _getCharactersState = MutableLiveData<UiState>()
    val getCharactersState: LiveData<UiState> = _getCharactersState

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _getCharactersState.postValue(UiState.Error(throwable))
        }) {
            _getCharactersState.postValue(UiState.Loading)
            val location = getLocation(locationId)
            val characterIds = location.residents
                .map { it.split("/").last().toInt() }

            val characters = getCharacters(characterIds)
            _location.postValue(location)
            _characters.postValue(characters)
            _getCharactersState.postValue(UiState.Complete)
        }
    }
}