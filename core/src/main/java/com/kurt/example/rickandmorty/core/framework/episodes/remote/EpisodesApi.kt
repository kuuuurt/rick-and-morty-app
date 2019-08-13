package com.kurt.example.rickandmorty.core.framework.episodes.remote

import com.kurt.example.rickandmorty.core.framework.models.RickAndMortyResponse
import com.kurt.example.rickandmorty.core.domain.entities.Episode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/06/2019
 */
interface EpisodesApi {
    @GET("episode/")
    suspend fun getEpisodes(@Query("page") page: Int? = null): RickAndMortyResponse<Episode>

    @GET("episode/{episodeId}")
    suspend fun getEpisode(@Path("episodeId") episodeId: Int): Episode
}