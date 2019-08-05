package com.kurt.example.rickandmorty.characters.di

import com.kurt.example.rickandmorty.characters.domain.usecases.GetCharacters
import com.kurt.example.rickandmorty.characters.presentation.characterslist.CharactersListViewModelFactory
import dagger.Module

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
@Module
class CharactersListModule {
    fun providesCharacterListViewModelFactory(getCharacters: GetCharacters) =
        CharactersListViewModelFactory(getCharacters)
}