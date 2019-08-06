package com.kurt.example.rickandmorty.core.di

import com.kurt.example.rickandmorty.core.data.characters.CharactersRemoteSource
import com.kurt.example.rickandmorty.core.domain.repositories.CharactersRepository
import dagger.Component

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
@Component(modules = [CharactersModule::class])
interface CoreComponent {
    fun provideCharactersRepository(): CharactersRepository
    fun provideCharactersRemoteSource(): CharactersRemoteSource
}