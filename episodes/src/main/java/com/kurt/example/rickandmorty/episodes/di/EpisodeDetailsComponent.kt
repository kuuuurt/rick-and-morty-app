package com.kurt.example.rickandmorty.episodes.di

import com.kurt.example.rickandmorty.core.di.CoreComponent
import com.kurt.example.rickandmorty.episodes.presentation.episodedetails.EpisodeDetailsFragment
import dagger.Component

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
@Component(dependencies = [CoreComponent::class], modules = [EpisodeDetailsModule::class])
interface EpisodeDetailsComponent {
    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)
}