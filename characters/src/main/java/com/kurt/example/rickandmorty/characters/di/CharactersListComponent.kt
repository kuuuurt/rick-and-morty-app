package com.kurt.example.rickandmorty.characters.di

import com.kurt.example.rickandmorty.characters.presentation.characterslist.CharactersListFragment
import dagger.Component

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
@Component(modules = [CharactersModule::class, CharactersListModule::class])
interface CharactersListComponent {
    fun inject(charactersListFragment: CharactersListFragment)
}