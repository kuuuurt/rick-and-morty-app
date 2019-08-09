package com.kurt.example.rickandmorty.core.di

import com.kurt.example.rickandmorty.core.data.characters.CharactersRemoteSource
import com.kurt.example.rickandmorty.core.data.episodes.EpisodesRemoteSource
import com.kurt.example.rickandmorty.core.domain.repositories.CharactersRepository
import com.kurt.example.rickandmorty.core.domain.repositories.EpisodesRepository
import dagger.Component

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
@Component(modules = [CharactersModule::class, EpisodesModule::class])
interface CoreComponent {
    fun provideCharactersRepository(): CharactersRepository
    fun provideCharactersRemoteSource(): CharactersRemoteSource
    fun provideEpisodesRepository(): EpisodesRepository
    fun provideEpisodesRemoteSource(): EpisodesRemoteSource
}