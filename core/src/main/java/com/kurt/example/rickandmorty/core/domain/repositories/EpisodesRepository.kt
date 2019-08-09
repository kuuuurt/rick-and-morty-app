package com.kurt.example.rickandmorty.core.domain.repositories

import com.kurt.example.rickandmorty.core.domain.entities.Episode

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
interface EpisodesRepository {
    suspend fun getEpisodes(page: Int? = null): List<Episode>
    suspend fun getEpisode(episodeId: Int): Episode
}