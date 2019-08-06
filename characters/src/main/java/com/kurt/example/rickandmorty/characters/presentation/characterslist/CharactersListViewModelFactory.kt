package com.kurt.example.rickandmorty.characters.presentation.characterslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kurt.example.rickandmorty.core.domain.usecases.GetCharacters
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
@Suppress("UNCHECKED_CAST")
class CharactersListViewModelFactory @Inject constructor(
    private val getCharacters: GetCharacters
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharactersListViewModel::class.java)) {
            return CharactersListViewModel(getCharacters) as T
        }
        throw IllegalArgumentException("ViewModel not found")
    }
}