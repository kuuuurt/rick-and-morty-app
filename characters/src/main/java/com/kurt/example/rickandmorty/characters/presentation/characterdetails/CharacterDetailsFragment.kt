package com.kurt.example.rickandmorty.characters.presentation.characterdetails

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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

    val args: CharacterDetailsFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtCharacterId by lazy { view.findViewById<TextView>(R.id.txt_character_id) }
        txtCharacterId.text = args.characterId.toString()
    }
}