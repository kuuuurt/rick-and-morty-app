package com.kurt.example.rickandmorty.locations.presentation.locationdetails

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.core.presentation.UiState
import com.kurt.example.rickandmorty.core.presentation.app.coreComponent
import com.kurt.example.rickandmorty.core.presentation.characters.CharactersListAdapter
import com.kurt.example.rickandmorty.core.presentation.utils.navigateUriWithDefaultOptions
import com.kurt.example.rickandmorty.core.presentation.views.EmptyView
import com.kurt.example.rickandmorty.core.presentation.views.LoadingView
import com.kurt.example.rickandmorty.locations.R
import com.kurt.example.rickandmorty.locations.di.DaggerLocationDetailsComponent
import com.kurt.example.rickandmorty.locations.di.LocationDetailsModule
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/13/2019
 */
class LocationDetailsFragment : BaseFragment<LocationDetailsViewModel>() {
    @Inject
    lateinit var factory: LocationDetailsViewModel.Factory

    override val viewModel: LocationDetailsViewModel by viewModels { factory }
    override val layout: Int = R.layout.fragment_location_details

    private lateinit var txtName: TextView
    private lateinit var txtDimension: TextView
    private lateinit var txtType: TextView

    private lateinit var grpLocation: Group
    private lateinit var loadingLocation: LoadingView
    private lateinit var emptyLocation: EmptyView

    private lateinit var recCharacters: RecyclerView
    private lateinit var loadingCharacters: LoadingView
    private lateinit var emptyCharacters: EmptyView

    private val charactersAdapter by lazy {
        CharactersListAdapter {
            findNavController().navigateUriWithDefaultOptions(
                Uri.parse("rickandmorty://characterdetails/$it"),
                R.id.location_details_fragment
            )
        }
    }

    private val args by navArgs<LocationDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DaggerLocationDetailsComponent
            .builder()
            .locationDetailsModule(LocationDetailsModule(args.locationId))
            .coreComponent(coreComponent())
            .build()
            .inject(this)

        txtName = view.findViewById(R.id.txt_name)
        txtDimension = view.findViewById(R.id.txt_dimension)
        txtType = view.findViewById(R.id.txt_type)

        grpLocation = view.findViewById(R.id.grp_location)
        loadingLocation = view.findViewById(R.id.loading_location)
        emptyLocation = view.findViewById(R.id.empty_location)

        recCharacters = view.findViewById(R.id.rec_characters)
        loadingCharacters = view.findViewById(R.id.loading_characters)
        emptyCharacters = view.findViewById(R.id.empty_characters)

        recCharacters.adapter = charactersAdapter

        viewModel.location.observe(this, Observer {
            txtName.text = it.name
            txtDimension.text = it.dimension
            txtType.text = it.type
        })

        viewModel.characters.observe(this, Observer {
            charactersAdapter.submitList(it)
        })

        viewModel.getLocationState.observe(this, Observer {
            grpLocation.visibility = if (it == UiState.Complete) View.VISIBLE else View.INVISIBLE
            loadingLocation.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyLocation.visibility = if (it is UiState.Error) View.VISIBLE else View.GONE
        })

        viewModel.getCharactersState.observe(this, Observer {
            recCharacters.visibility = if (it == UiState.Complete) View.VISIBLE else View.INVISIBLE
            loadingCharacters.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyCharacters.visibility =
                if (it == UiState.Empty || it is UiState.Error) View.VISIBLE else View.GONE
        })
    }

}