package com.kurt.example.rickandmorty.episodes.presentation.episodedetails

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.core.presentation.UiState
import com.kurt.example.rickandmorty.core.presentation.app.coreComponent
import com.kurt.example.rickandmorty.core.presentation.characters.CharactersListAdapter
import com.kurt.example.rickandmorty.core.presentation.views.EmptyView
import com.kurt.example.rickandmorty.core.presentation.views.LoadingView
import com.kurt.example.rickandmorty.episodes.R
import com.kurt.example.rickandmorty.episodes.di.DaggerEpisodeDetailsComponent
import com.kurt.example.rickandmorty.episodes.di.DaggerEpisodesListComponent
import com.kurt.example.rickandmorty.episodes.di.EpisodeDetailsModule
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class EpisodeDetailsFragment : BaseFragment<EpisodeDetailsViewModel>() {
    @Inject
    lateinit var factory: EpisodeDetailsViewModel.Factory

    override val viewModel: EpisodeDetailsViewModel by viewModels(factoryProducer = { factory })
    override val layout = R.layout.fragment_episode_details

    private lateinit var txtTitle: TextView
    private lateinit var txtEpisode: TextView
    private lateinit var txtAirDate: TextView

    private lateinit var grpEpisode: Group
    private lateinit var loadingEpisode: LoadingView
    private lateinit var emptyEpisode: EmptyView

    private lateinit var recCharacters: RecyclerView
    private lateinit var loadingCharacters: LoadingView
    private lateinit var emptyCharacters: EmptyView

    private val charactersAdapter by lazy { CharactersListAdapter {} }

    private val args by navArgs<EpisodeDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerEpisodeDetailsComponent
            .builder()
            .episodeDetailsModule(EpisodeDetailsModule(args.episodeId))
            .coreComponent(coreComponent())
            .build()
            .inject(this)

        txtTitle = view.findViewById(R.id.txt_title)
        txtEpisode = view.findViewById(R.id.txt_episode)
        txtAirDate = view.findViewById(R.id.txt_air_date)

        grpEpisode = view.findViewById(R.id.grp_episode)
        loadingEpisode = view.findViewById(R.id.loading_episode)
        emptyEpisode = view.findViewById(R.id.empty_episode)

        recCharacters = view.findViewById(R.id.rec_characters)
        loadingCharacters = view.findViewById(R.id.loading_characters)
        emptyCharacters = view.findViewById(R.id.empty_characters)

        recCharacters.adapter = charactersAdapter

        viewModel.episode.observe(this, Observer {
            txtTitle.text = it.name
            txtEpisode.text = it.episode
            txtAirDate.text = it.airDate
        })

        viewModel.characters.observe(this, Observer {
            charactersAdapter.submitList(it)
        })

        viewModel.getEpisodeState.observe(this, Observer {
            grpEpisode.visibility = if (it == UiState.Complete) View.VISIBLE else View.INVISIBLE
            loadingEpisode.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyEpisode.visibility = if (it is UiState.Error) View.VISIBLE else View.GONE
        })

        viewModel.getCharactersState.observe(this, Observer {
            recCharacters.visibility = if (it == UiState.Complete) View.VISIBLE else View.INVISIBLE
            loadingCharacters.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyCharacters.visibility =
                if (it == UiState.Empty || it is UiState.Error) View.VISIBLE else View.GONE
        })
    }

}