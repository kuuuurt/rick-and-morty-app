package com.kurt.example.rickandmorty.episodes.presentation.episodeslist

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import com.kurt.example.rickandmorty.core.domain.usecases.GetEpisodes

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class EpisodesListViewModel(getEpisodes: GetEpisodes) : ViewModel() {
    val episodesDataSourceFactory = EpisodesDataSource.Factory(getEpisodes, viewModelScope)
    val episodes = LivePagedListBuilder(episodesDataSourceFactory, 20).build()
    val getEpisodesState = Transformations.switchMap(episodesDataSourceFactory.sourceLiveData) {
        it.getEpisodesState
    }
}