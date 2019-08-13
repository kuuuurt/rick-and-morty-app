package com.kurt.example.rickandmorty.episodes.presentation.episodeslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.core.presentation.UiState
import com.kurt.example.rickandmorty.core.presentation.app.coreComponent
import com.kurt.example.rickandmorty.core.presentation.views.EmptyView
import com.kurt.example.rickandmorty.core.presentation.views.LoadingView
import com.kurt.example.rickandmorty.episodes.R
import com.kurt.example.rickandmorty.episodes.di.DaggerEpisodesListComponent
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class EpisodesListFragment : BaseFragment<EpisodesListViewModel>() {
    @Inject
    lateinit var factory: EpisodesListViewModel.Factory

    override val viewModel: EpisodesListViewModel by viewModels(factoryProducer = { factory })
    override val layout: Int = R.layout.fragment_episodes_list

    private val episodesAdapter by lazy {
        EpisodesPagedListAdapter {  }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerEpisodesListComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)


        val recEpisodes by lazy { view.findViewById<RecyclerView>(R.id.rec_episodes) }
        val loadingEpisodes by lazy { view.findViewById<LoadingView>(R.id.loading_episodes) }
        val emptyEpisodes by lazy { view.findViewById<EmptyView>(R.id.empty_episodes) }

        recEpisodes.adapter = episodesAdapter

        viewModel.episodes.observe(this, Observer {
            episodesAdapter.submitList(it)
        })

        viewModel.getEpisodesState.observe(this, Observer {
            recEpisodes.visibility = if (it == UiState.Complete) View.VISIBLE else View.GONE
            loadingEpisodes.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyEpisodes.visibility = if (it is UiState.Error || it == UiState.Empty) View.VISIBLE else View.GONE
        })
    }
}