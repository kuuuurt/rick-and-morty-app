package com.kurt.example.rickandmorty.core.framework.characters.remote

import com.kurt.example.rickandmorty.core.framework.models.RickAndMortyResponse
import com.kurt.example.rickandmorty.core.domain.entities.Character
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
interface CharactersApi {
    @GET("character/")
    suspend fun getAllCharacters(@Query("page") page: Int? = null): RickAndMortyResponse<Character>

    @GET("character/{characterIds}")
    suspend fun getCharacters(@Path("characterIds") characterIds: List<Int>): List<Character>

    @GET("character/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int): Character
}