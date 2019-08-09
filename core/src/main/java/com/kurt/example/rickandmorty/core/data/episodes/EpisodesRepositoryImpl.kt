package com.kurt.example.rickandmorty.core.data.episodes

import com.kurt.example.rickandmorty.core.domain.repositories.EpisodesRepository
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
class EpisodesRepositoryImpl @Inject constructor(
    private val remoteSource: EpisodesRemoteSource
) : EpisodesRepository {
    override suspend fun getEpisodes(page: Int?) = remoteSource.getEpisodes(page)
    override suspend fun getEpisode(episodeId: Int) = remoteSource.getEpisode(episodeId)
}