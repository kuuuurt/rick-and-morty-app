package com.kurt.example.rickandmorty.characters.presentation.characterdetails

import androidx.lifecycle.*
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacter
import com.kurt.example.rickandmorty.core.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/01/2019
 */
class CharacterDetailsViewModel(
    private val characterId: Int,
    private val getCharacter: GetCharacter
) : ViewModel() {
    class Factory(
        private val characterId: Int,
        private val getCharacter: GetCharacter
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharacterDetailsViewModel::class.java)) {
                return CharacterDetailsViewModel(characterId, getCharacter) as T
            }
            throw IllegalArgumentException("ViewModel not found.")
        }
    }

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    private val _getCharacterState = MutableLiveData<UiState>()
    val getCharacterState: LiveData<UiState> = _getCharacterState

    init {
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            _getCharacterState.postValue(UiState.Error(throwable))
        }) {
            _getCharacterState.postValue(UiState.Loading)
            val character = getCharacter(characterId)
            _character.postValue(character)
            _getCharacterState.postValue(UiState.Complete)
        }
    }
}