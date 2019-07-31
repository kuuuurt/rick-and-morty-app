package com.kurt.example.rickandmorty.locations.presentation.locationslist

import androidx.fragment.app.viewModels
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.locations.R

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 07/31/2019
 */
class LocationsListFragment : BaseFragment<LocationsListViewModel>() {
    override val viewModel: LocationsListViewModel by viewModels()
    override val layout: Int = R.layout.fragment_locations_list

}