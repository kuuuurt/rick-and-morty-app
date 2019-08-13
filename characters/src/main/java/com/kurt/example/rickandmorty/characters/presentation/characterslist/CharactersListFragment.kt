package com.kurt.example.rickandmorty.characters.presentation.characterslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kurt.example.rickandmorty.characters.R
import com.kurt.example.rickandmorty.characters.di.DaggerCharactersListComponent
import com.kurt.example.rickandmorty.characters.presentation.characterdetails.CharacterDetailsFragment
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.core.presentation.UiState
import com.kurt.example.rickandmorty.core.presentation.app.coreComponent
import com.kurt.example.rickandmorty.core.presentation.views.EmptyView
import com.kurt.example.rickandmorty.core.presentation.views.LoadingView
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 07/31/2019
 */
class CharactersListFragment : BaseFragment<CharactersListViewModel>() {
    @Inject
    lateinit var factory: CharactersListViewModel.Factory

    override val viewModel: CharactersListViewModel by viewModels(factoryProducer = { factory })
    override val layout: Int = R.layout.fragment_characters_list

    private val charactersAdapter by lazy { CharactersPagedListAdapter {
        val direction = CharactersListFragmentDirections.actionViewCharacterDetails(it)
        findNavController().navigate(direction)
    } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerCharactersListComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)


        val recCharacters by lazy { view.findViewById<RecyclerView>(R.id.rec_characters) }
        val loadingCharacters by lazy { view.findViewById<LoadingView>(R.id.loading_characters) }
        val emptyCharacters by lazy { view.findViewById<EmptyView>(R.id.empty_characters) }

        recCharacters.adapter = charactersAdapter

        viewModel.characters.observe(this, Observer {
            charactersAdapter.submitList(it)
        })

        viewModel.getCharactersState.observe(this, Observer {
            recCharacters.visibility = if (it == UiState.Complete) View.VISIBLE else View.GONE
            loadingCharacters.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyCharacters.visibility = if (it is UiState.Error || it == UiState.Empty) View.VISIBLE else View.GONE
        })
    }
}