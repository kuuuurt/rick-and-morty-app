package com.kurt.example.rickandmorty.characters.presentation.characterdetails

import android.net.Uri
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kurt.example.rickandmorty.characters.R
import com.kurt.example.rickandmorty.characters.di.CharacterDetailsModule
import com.kurt.example.rickandmorty.characters.di.DaggerCharacterDetailsComponent
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.core.presentation.UiState
import com.kurt.example.rickandmorty.core.presentation.app.GlideApp
import com.kurt.example.rickandmorty.core.presentation.app.coreComponent
import com.kurt.example.rickandmorty.core.presentation.episodes.EpisodesListAdapter
import com.kurt.example.rickandmorty.core.presentation.utils.navigateUriWithDefaultOptions
import com.kurt.example.rickandmorty.core.presentation.views.EmptyView
import com.kurt.example.rickandmorty.core.presentation.views.LoadingView
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/01/2019
 */
class CharacterDetailsFragment : BaseFragment<CharacterDetailsViewModel>() {
    @Inject
    lateinit var factory: CharacterDetailsViewModel.Factory

    override val viewModel: CharacterDetailsViewModel by viewModels(factoryProducer = { factory })
    override val layout: Int = R.layout.fragment_character_details

    private val args: CharacterDetailsFragmentArgs by navArgs()

    private lateinit var imgCharacter: ImageView
    private lateinit var txtName: TextView
    private lateinit var txtSpecies: TextView
    private lateinit var txtStatus: TextView
    private lateinit var txtType: TextView
    private lateinit var txtLocation: TextView
    private lateinit var txtOrigin: TextView

    private lateinit var grpCharacter: Group
    private lateinit var loadingCharacter: LoadingView
    private lateinit var emptyCharacter: EmptyView

    private lateinit var recEpisodes: RecyclerView
    private lateinit var loadingEpisodes: LoadingView
    private lateinit var emptyEpisodes: EmptyView

    private val episodesAdapter by lazy {
        EpisodesListAdapter {
            findNavController().navigateUriWithDefaultOptions(
                Uri.parse("rickandmorty://episodedetails/$it"),
                R.id.character_details_fragment
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerCharacterDetailsComponent
            .builder()
            .characterDetailsModule(CharacterDetailsModule(args.characterId))
            .coreComponent(coreComponent())
            .build()
            .inject(this)

        imgCharacter = view.findViewById(R.id.img_character)
        txtName = view.findViewById(R.id.txt_name)
        txtSpecies = view.findViewById(R.id.txt_species)
        txtStatus = view.findViewById(R.id.txt_status)
        txtType = view.findViewById(R.id.txt_type)
        txtLocation = view.findViewById(R.id.txt_location)
        txtOrigin = view.findViewById(R.id.txt_origin)

        grpCharacter = view.findViewById(R.id.grp_character)
        loadingCharacter = view.findViewById(R.id.loading_character)
        emptyCharacter = view.findViewById(R.id.empty_character)

        recEpisodes = view.findViewById(R.id.rec_episodes)
        loadingEpisodes = view.findViewById(R.id.loading_episodes)
        emptyEpisodes = view.findViewById(R.id.empty_episodes)

        recEpisodes.adapter = episodesAdapter

        Glide.with(requireContext())
            .load(args.imgUrl)
            .into(imgCharacter)

        ViewCompat.setTransitionName(imgCharacter, "imgCharacter")

        viewModel.character.observe(this, Observer {
            GlideApp.with(requireContext())
                .load(it.image)
                .into(imgCharacter)

            txtName.text = it.name
            txtSpecies.text = it.species
            txtStatus.text = it.status
            txtType.text = it.type
            txtLocation.text = it.location.name
            txtOrigin.text = it.origin.name
        })

        viewModel.episodes.observe(this, Observer {
            episodesAdapter.submitList(it)
        })

        viewModel.getCharacterState.observe(this, Observer {
//            grpCharacter.visibility = if (it == UiState.Complete) View.VISIBLE else View.INVISIBLE
//            loadingCharacter.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
//            emptyCharacter.visibility = if (it is UiState.Error) View.VISIBLE else View.GONE
        })

        viewModel.getEpisodesState.observe(this, Observer {
            recEpisodes.visibility = if (it == UiState.Complete) View.VISIBLE else View.INVISIBLE
            loadingEpisodes.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyEpisodes.visibility =
                if (it is UiState.Error || it == UiState.Empty) View.VISIBLE else View.GONE
        })
    }
}