package com.kurt.example.rickandmorty.characters.framework.remote

import com.kurt.example.rickandmorty.characters.data.CharactersRemoteSource
import com.kurt.example.rickandmorty.core.domain.entities.Character
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

    override suspend fun getCharacters() = api.getCharacters().results
    override suspend fun getCharacter(characterId: Int) = api.getCharacter(characterId)

}