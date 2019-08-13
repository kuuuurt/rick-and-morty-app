package com.kurt.example.rickandmorty.episodes.di

import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacter
import com.kurt.example.rickandmorty.core.domain.usecases.GetEpisode
import com.kurt.example.rickandmorty.episodes.presentation.episodedetails.EpisodeDetailsViewModel
import dagger.Module
import dagger.Provides

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
@Module
class EpisodeDetailsModule(private val episodeId: Int) {
    @Provides
    fun providesEpisodeDetailsViewModelFactory(getEpisode: GetEpisode, getCharacter: GetCharacter) =
        EpisodeDetailsViewModel.Factory(episodeId, getEpisode, getCharacter)
}