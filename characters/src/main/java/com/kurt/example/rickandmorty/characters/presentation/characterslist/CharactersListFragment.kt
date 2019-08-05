package com.kurt.example.rickandmorty.characters.presentation.characterslist

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnTest by lazy { view.findViewById<Button>(R.id.btn_test) }

        btnTest.setOnClickListener {
            it.findNavController().navigate(R.id.action_view_character_details)
        }
    }
}