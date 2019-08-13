package com.kurt.example.rickandmorty.episodes.presentation.episodeslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.kurt.example.rickandmorty.core.domain.entities.Episode
import com.kurt.example.rickandmorty.core.domain.usecases.GetEpisodes
import com.kurt.example.rickandmorty.core.presentation.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class EpisodesDataSource(
    private val getEpisodes: GetEpisodes,
    private val scope: CoroutineScope
) : PageKeyedDataSource<Int, Episode>() {

    class Factory(
        private val getEpisodes: GetEpisodes,
        private val scope: CoroutineScope
    ) : DataSource.Factory<Int, Episode>() {
        val sourceLiveData = MutableLiveData<EpisodesDataSource>()
        private lateinit var latestSource: EpisodesDataSource

        override fun create(): DataSource<Int, Episode> {
            latestSource = EpisodesDataSource(getEpisodes, scope)
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }

    private val _getEpisodesState = MutableLiveData<UiState>()
    val getEpisodesState: LiveData<UiState> = _getEpisodesState

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Episode>
    ) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getEpisodesState.postValue(UiState.Error(throwable))
        }) {
            _getEpisodesState.postValue(UiState.Loading)
            val episodes = getEpisodes()
            callback.onResult(episodes, null, 2)
            _getEpisodesState.postValue(UiState.Complete)
            if (episodes.isEmpty()) {
                _getEpisodesState.postValue(UiState.Empty)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Episode>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getEpisodesState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(getEpisodes(params.key), params.key + 1)
            _getEpisodesState.postValue(UiState.Complete)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Episode>) {
        scope.launch(CoroutineExceptionHandler { _, throwable ->
            _getEpisodesState.postValue(UiState.Error(throwable))
        }) {
            callback.onResult(getEpisodes(params.key), params.key - 1)
            _getEpisodesState.postValue(UiState.Complete)
        }
    }

}