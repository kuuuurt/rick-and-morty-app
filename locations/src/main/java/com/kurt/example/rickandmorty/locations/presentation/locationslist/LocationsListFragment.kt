package com.kurt.example.rickandmorty.locations.presentation.locationslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.core.presentation.UiState
import com.kurt.example.rickandmorty.core.presentation.app.coreComponent
import com.kurt.example.rickandmorty.core.presentation.views.EmptyView
import com.kurt.example.rickandmorty.core.presentation.views.LoadingView
import com.kurt.example.rickandmorty.locations.R
import com.kurt.example.rickandmorty.locations.di.DaggerLocationsListComponent
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 07/31/2019
 */
class LocationsListFragment : BaseFragment<LocationsListViewModel>() {
    @Inject
    lateinit var factory: LocationsListViewModel.Factory

    override val viewModel: LocationsListViewModel by viewModels(factoryProducer = { factory })
    override val layout: Int = R.layout.fragment_locations_list

    private lateinit var recLocations: RecyclerView
    private lateinit var loadingLocations: LoadingView
    private lateinit var emptyLocations: EmptyView

    private val locationsAdapter by lazy { LocationsPagedListAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerLocationsListComponent
            .builder()
            .coreComponent(coreComponent())
            .build()
            .inject(this)

        recLocations = view.findViewById(R.id.rec_locations)
        loadingLocations = view.findViewById(R.id.loading_locations)
        emptyLocations = view.findViewById(R.id.empty_locations)

        recLocations.adapter = locationsAdapter

        viewModel.locations.observe(this, Observer {
            locationsAdapter.submitList(it)
        })

        viewModel.getLocationsState.observe(this, Observer {
            recLocations.visibility = if (it == UiState.Complete) View.VISIBLE else View.GONE
            loadingLocations.visibility = if (it == UiState.Loading) View.VISIBLE else View.GONE
            emptyLocations.visibility =
                if (it is UiState.Error || it == UiState.Empty) View.VISIBLE else View.GONE
        })
    }

}