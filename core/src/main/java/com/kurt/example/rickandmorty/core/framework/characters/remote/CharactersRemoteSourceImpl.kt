package com.kurt.example.rickandmorty.core.framework.characters.remote

import com.kurt.example.rickandmorty.core.data.characters.CharactersRemoteSource
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class CharactersRemoteSourceImpl @Inject constructor() : CharactersRemoteSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    private val api = retrofit.create(CharactersApi::class.java)

    override suspend fun getCharacters(page: Int?) = api.getCharacters(page).results
    override suspend fun getCharacter(characterId: Int) = api.getCharacter(characterId)

}