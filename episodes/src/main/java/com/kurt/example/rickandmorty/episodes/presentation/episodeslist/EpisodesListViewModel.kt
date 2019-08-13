package com.kurt.example.rickandmorty.episodes.presentation.episodeslist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import com.kurt.example.rickandmorty.core.domain.usecases.GetAllEpisodes
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class EpisodesListViewModel(getAllEpisodes: GetAllEpisodes) : ViewModel() {

    class Factory @Inject constructor(
        private val getAllEpisodes: GetAllEpisodes
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EpisodesListViewModel::class.java)) {
                return EpisodesListViewModel(getAllEpisodes) as T
            }
            throw IllegalArgumentException("ViewModel not found")
        }
    }

    val episodesDataSourceFactory = EpisodesDataSource.Factory(getAllEpisodes, viewModelScope)
    val episodes = LivePagedListBuilder(episodesDataSourceFactory, 20).build()
    val getEpisodesState = Transformations.switchMap(episodesDataSourceFactory.sourceLiveData) {
        it.getEpisodesState
    }
}