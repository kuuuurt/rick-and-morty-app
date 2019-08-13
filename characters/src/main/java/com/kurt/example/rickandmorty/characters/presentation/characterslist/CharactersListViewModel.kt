package com.kurt.example.rickandmorty.characters.presentation.characterslist

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kurt.example.rickandmorty.core.domain.usecases.GetAllCharacters
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.presentation.UiState
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 07/31/2019
 */
class CharactersListViewModel(getAllCharacters: GetAllCharacters) : ViewModel() {

    class Factory @Inject constructor(
        private val getAllCharacters: GetAllCharacters
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CharactersListViewModel::class.java)) {
                return CharactersListViewModel(getAllCharacters) as T
            }
            throw IllegalArgumentException("ViewModel not found")
        }
    }
    private val charactersDataSourceFactory =
        CharactersDataSource.Factory(getAllCharacters, viewModelScope)

    val characters: LiveData<PagedList<Character>> =
        LivePagedListBuilder(charactersDataSourceFactory, 20).build()

    val getCharactersState: LiveData<UiState> =
        Transformations.switchMap(charactersDataSourceFactory.sourceLiveData) {
            it.getCharactersState
        }
}