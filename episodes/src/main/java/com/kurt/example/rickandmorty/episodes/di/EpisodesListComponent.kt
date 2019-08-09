package com.kurt.example.rickandmorty.episodes.di

import com.kurt.example.rickandmorty.core.di.CoreComponent
import com.kurt.example.rickandmorty.episodes.presentation.episodeslist.EpisodesListFragment
import dagger.Component

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/09/2019
 */
@Component(dependencies = [CoreComponent::class])
interface EpisodesListComponent {
    fun inject(episodesListFragment: EpisodesListFragment)
}