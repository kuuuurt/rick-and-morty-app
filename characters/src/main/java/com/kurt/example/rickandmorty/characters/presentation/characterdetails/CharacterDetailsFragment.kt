package com.kurt.example.rickandmorty.characters.presentation.characterdetails

import androidx.fragment.app.viewModels
import com.kurt.example.rickandmorty.characters.R
import com.kurt.example.rickandmorty.core.presentation.BaseFragment

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/01/2019
 */
class CharacterDetailsFragment : BaseFragment<CharacterDetailsViewModel>() {
    override val viewModel: CharacterDetailsViewModel by viewModels()
    override val layout: Int = R.layout.fragment_character_details

}