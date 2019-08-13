package com.kurt.example.rickandmorty.core.domain.repositories

import com.kurt.example.rickandmorty.core.domain.entities.Episode
import com.kurt.example.rickandmorty.core.domain.entities.Location

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
interface EpisodesRepository {
    suspend fun getAllEpisodes(page: Int? = null): List<Episode>
    suspend fun getEpisodes(episodeIds: List<Int>): List<Episode>
    suspend fun getEpisode(episodeId: Int): Episode
}