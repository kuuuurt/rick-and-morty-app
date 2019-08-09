package com.kurt.example.rickandmorty.episodes.presentation.episodeslist

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kurt.example.rickandmorty.core.domain.entities.Episode
import com.kurt.example.rickandmorty.episodes.R
import com.marvel.example.core.presentation.BasePagedListAdapter

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class EpisodesPagedListAdapter(val onClick: (Int) -> Unit) : BasePagedListAdapter<Episode>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {
    override val itemLayout = R.layout.list_item_episode

    override fun bindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val episode = getItem(position)

        if (episode != null) {
            holder.itemView.apply {
                val txtTitle by lazy { findViewById<TextView>(R.id.txt_title) }
                val txtEpisode by lazy { findViewById<TextView>(R.id.txt_episode) }

                txtTitle.text = episode.name
                txtEpisode.text = episode.episode

                setOnClickListener { onClick(episode.id) }
            }
        }
    }
}