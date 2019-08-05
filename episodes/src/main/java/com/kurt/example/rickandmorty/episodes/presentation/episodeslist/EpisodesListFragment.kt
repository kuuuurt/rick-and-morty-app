package com.kurt.example.rickandmorty.episodes.presentation.episodeslist

import androidx.fragment.app.viewModels
import com.kurt.example.rickandmorty.core.presentation.BaseFragment
import com.kurt.example.rickandmorty.episodes.R

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class EpisodesListFragment : BaseFragment<EpisodesListViewModel>() {
    override val viewModel: EpisodesListViewModel by viewModels()
    override val layout: Int = R.layout.fragment_episodes_list

}