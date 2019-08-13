package com.kurt.example.rickandmorty.core.data.episodes

import com.kurt.example.rickandmorty.core.domain.repositories.EpisodesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
    override suspend fun getAllEpisodes(page: Int?) = withContext(Dispatchers.IO) {
        remoteSource.getAllEpisodes(page)
    }

    override suspend fun getEpisodes(episodeIds: List<Int>) = withContext(Dispatchers.IO) {
        remoteSource.getEpisodes(episodeIds)
    }

    override suspend fun getEpisode(episodeId: Int) = withContext(Dispatchers.IO) {
        remoteSource.getEpisode(episodeId)
    }
}