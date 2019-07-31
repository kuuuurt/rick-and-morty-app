package com.kurt.example.rickandmorty.characters.presentation.characterslist

import androidx.fragment.app.viewModels
import com.kurt.example.rickandmorty.characters.R
import com.kurt.example.rickandmorty.core.presentation.BaseFragment

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 07/31/2019
 */
class CharactersListFragment : BaseFragment<CharactersListViewModel>() {
    override val viewModel: CharactersListViewModel by viewModels()
    override val layout: Int = R.layout.fragment_characters_list
}