package com.kurt.example.rickandmorty.core.framework.episodes.remote

import com.kurt.example.rickandmorty.core.data.episodes.EpisodesRemoteSource
import com.kurt.example.rickandmorty.core.domain.entities.Episode
import com.kurt.example.rickandmorty.core.framework.BaseRemoteSource
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
class EpisodesRemoteSourceImpl @Inject constructor() : BaseRemoteSource<EpisodesApi>(
    EpisodesApi::class.java
), EpisodesRemoteSource {
    override suspend fun getAllEpisodes(page: Int?): List<Episode> = try {
        api.getAllEpisodes(page).results
    } catch (exception: HttpException) {
        if (exception.code() == 404) listOf() else throw exception
    }
    override suspend fun getEpisodes(episodeIds: List<Int>): List<Episode> = api.getEpisodes(episodeIds)
    override suspend fun getEpisode(episodeId: Int) = api.getEpisode(episodeId)
}