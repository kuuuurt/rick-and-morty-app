package com.kurt.example.rickandmorty.episodes.presentation.episodeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kurt.example.rickandmorty.core.domain.usecases.GetEpisodes
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/09/2019
 */
class EpisodesListViewModelFactory @Inject constructor(
    private val getEpisodes: GetEpisodes
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EpisodesListViewModel::class.java)) {
            return EpisodesListViewModel(getEpisodes) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }
}