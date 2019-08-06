package com.kurt.example.rickandmorty.core.di

import com.kurt.example.rickandmorty.core.data.characters.CharactersRemoteSource
import com.kurt.example.rickandmorty.core.data.characters.CharactersRepositoryImpl
import com.kurt.example.rickandmorty.core.domain.repositories.CharactersRepository
import com.kurt.example.rickandmorty.core.framework.characters.remote.CharactersRemoteSourceImpl
import dagger.Binds
import dagger.Module

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
@Module
abstract class CharactersModule {
    @Binds
    abstract fun provideCharactersRepository(charactersRepositoryImpl: CharactersRepositoryImpl): CharactersRepository

    @Binds
    abstract fun provideCharactersRemoteSource(charactersRemoteSourceImpl: CharactersRemoteSourceImpl): CharactersRemoteSource
}