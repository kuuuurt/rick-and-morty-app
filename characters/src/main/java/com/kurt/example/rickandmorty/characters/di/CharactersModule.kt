package com.kurt.example.rickandmorty.characters.di

import com.kurt.example.rickandmorty.characters.data.CharactersRemoteSource
import com.kurt.example.rickandmorty.characters.data.CharactersRepositoryImpl
import com.kurt.example.rickandmorty.characters.domain.repositories.CharactersRepository
import com.kurt.example.rickandmorty.characters.framework.remote.CharactersRemoteSourceImpl
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