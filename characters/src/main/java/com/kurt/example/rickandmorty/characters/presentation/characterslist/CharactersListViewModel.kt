package com.kurt.example.rickandmorty.characters.presentation.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
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
    private val charactersDataSourceFactory =
        CharactersDataSource.Factory(getCharacters, viewModelScope)

    val characters: LiveData<PagedList<Character>> =
        LivePagedListBuilder(charactersDataSourceFactory, 20).build()

    val getCharactersState: LiveData<UiState> =
        Transformations.switchMap(charactersDataSourceFactory.sourceLiveData) {
            it.getCharactersState
        }
}