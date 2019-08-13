package com.kurt.example.rickandmorty.characters.presentation.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.kurt.example.rickandmorty.core.domain.usecases.GetAllCharacters
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class CharactersDataSource(
    private val getAllCharacters: GetAllCharacters,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Character>() {

    class Factory(
        private val getAllCharacters: GetAllCharacters,
        private val scope: CoroutineScope
    ) : DataSource.Factory<Int, Character>() {
        val sourceLiveData = MutableLiveData<CharactersDataSource>()
        private lateinit var latestSource: CharactersDataSource

        override fun create(): DataSource<Int, Character> {
            latestSource = CharactersDataSource(getAllCharacters, scope)
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }

    private val _getCharactersState = MutableLiveData<UiState>()
    val getCharactersState: LiveData<UiState> = _getCharactersState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getCharactersState.postValue(UiState.Error(throwable))
        }) {
            _getCharactersState.postValue(UiState.Loading)
            val characters = getAllCharacters()
            callback.onResult(characters, null, 2)
            _getCharactersState.postValue(UiState.Complete)
            if (characters.isEmpty()) {
                _getCharactersState.postValue(UiState.Empty)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getCharactersState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(getAllCharacters(params.key), params.key + 1)
            _getCharactersState.postValue(UiState.Complete)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getCharactersState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(getAllCharacters(params.key), params.key - 1)
            _getCharactersState.postValue(UiState.Complete)
        }
    }

}