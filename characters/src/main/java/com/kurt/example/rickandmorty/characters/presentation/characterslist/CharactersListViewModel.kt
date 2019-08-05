package com.kurt.example.rickandmorty.characters.presentation.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurt.example.rickandmorty.characters.domain.usecases.GetCharacters
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 07/31/2019
 */
class CharactersListViewModel(
    private val getCharacters: GetCharacters
) : ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters

    private val _getCharactersState = MutableLiveData<UiState>()
    val getCharactersState: LiveData<UiState> = _getCharactersState

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _getCharactersState.postValue(UiState.Error(throwable))
        }) {
            _getCharactersState.postValue(UiState.Loading)
            _characters.postValue(getCharacters.invoke())
            _getCharactersState.postValue(UiState.Complete)
        }
    }
}