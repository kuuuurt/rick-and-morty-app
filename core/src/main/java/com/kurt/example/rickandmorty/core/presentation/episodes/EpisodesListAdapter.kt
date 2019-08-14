package com.kurt.example.rickandmorty.core.presentation.episodes

import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kurt.example.rickandmorty.characters.presentation.characterdetails.CharacterDetailsFragmentArgs
import com.kurt.example.rickandmorty.core.NavMainDirections
import com.kurt.example.rickandmorty.core.R
import com.kurt.example.rickandmorty.core.domain.entities.Episode
import com.kurt.example.rickandmorty.core.presentation.utils.createDefaultNavOptions
import com.kurt.example.rickandmorty.core.presentation.utils.navigateUriWithDefaultOptions
import com.kurt.example.rickandmorty.episodes.presentation.episodedetails.EpisodeDetailsFragmentArgs
import com.marvel.example.core.presentation.BaseListAdapter

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class EpisodesListAdapter : BaseListAdapter<Episode>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override val itemLayout = R.layout.list_item_episode

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episode = getItem(position)

        holder.itemView.apply {
            val txtTitle by lazy { findViewById<TextView>(R.id.txt_title) }
            val txtEpisode by lazy { findViewById<TextView>(R.id.txt_episode) }

            txtTitle.text = episode.name
            txtEpisode.text = episode.episode

            setOnClickListener {
                ViewCompat.setTransitionName(txtTitle, "txtTitle")
                ViewCompat.setTransitionName(txtEpisode, "txtEpisode")
                val extras = FragmentNavigatorExtras(
                    txtTitle to txtTitle.transitionName,
                    txtEpisode to txtEpisode.transitionName
                )
                val directions = NavMainDirections.actionViewEpisodeDetails(episode.id, episode.name, episode.episode)
                val navController = findNavController()
                navController.navigate(directions, extras)
            }
        }
    }
}