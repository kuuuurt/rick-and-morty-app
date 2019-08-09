package com.kurt.example.rickandmorty.core.di

import com.kurt.example.rickandmorty.core.data.characters.CharactersRemoteSource
import com.kurt.example.rickandmorty.core.data.characters.CharactersRepositoryImpl
import com.kurt.example.rickandmorty.core.data.episodes.EpisodesRemoteSource
import com.kurt.example.rickandmorty.core.data.episodes.EpisodesRepositoryImpl
import com.kurt.example.rickandmorty.core.domain.repositories.CharactersRepository
import com.kurt.example.rickandmorty.core.domain.repositories.EpisodesRepository
import com.kurt.example.rickandmorty.core.framework.characters.remote.CharactersRemoteSourceImpl
import com.kurt.example.rickandmorty.core.framework.episodes.remote.EpisodesRemoteSourceImpl
import dagger.Binds
import dagger.Module

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
@Module
abstract class EpisodesModule {
    @Binds
    abstract fun provideEpisodesRepository(episodesRepositoryImpl: EpisodesRepositoryImpl): EpisodesRepository

    @Binds
    abstract fun provideEpisodesRemoteSource(episodesRemoteSourceImpl: EpisodesRemoteSourceImpl): EpisodesRemoteSource
}