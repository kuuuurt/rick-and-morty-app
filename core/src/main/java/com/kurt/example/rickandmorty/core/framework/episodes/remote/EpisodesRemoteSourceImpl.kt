package com.kurt.example.rickandmorty.core.framework.episodes.remote

import com.kurt.example.rickandmorty.core.data.episodes.EpisodesRemoteSource
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
class EpisodesRemoteSourceImpl @Inject constructor() : EpisodesRemoteSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api = retrofit.create(EpisodesApi::class.java)

    override suspend fun getEpisodes(page: Int?) = api.getEpisodes(page).results
    override suspend fun getEpisode(episodeId: Int) = api.getEpisode(episodeId)
}