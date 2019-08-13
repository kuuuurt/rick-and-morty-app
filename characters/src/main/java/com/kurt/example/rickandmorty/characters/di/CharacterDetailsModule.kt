package com.kurt.example.rickandmorty.characters.di

import com.kurt.example.rickandmorty.characters.presentation.characterdetails.CharacterDetailsViewModel
import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacter
import com.kurt.example.rickandmorty.core.domain.usecases.GetEpisode
import dagger.Module
import dagger.Provides

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
@Module
class CharacterDetailsModule(private val characterId: Int) {
    @Provides
    fun provideCharacterDetailsViewModelFactory(getCharacter: GetCharacter, getEpisode: GetEpisode) =
        CharacterDetailsViewModel.Factory(characterId, getCharacter, getEpisode)
}
