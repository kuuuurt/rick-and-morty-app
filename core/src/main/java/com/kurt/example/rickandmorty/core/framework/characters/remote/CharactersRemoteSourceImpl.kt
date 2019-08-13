package com.kurt.example.rickandmorty.core.framework.characters.remote

import com.kurt.example.rickandmorty.core.data.characters.CharactersRemoteSource
import com.kurt.example.rickandmorty.core.domain.entities.Character
import com.kurt.example.rickandmorty.core.framework.BaseRemoteSource
import retrofit2.HttpException
import javax.inject.Inject

/**
 * Copyright 2019, Kurt Renzo Acosta, All rights reserved.
 *
 * @author Kurt Renzo Acosta
 * @since 08/05/2019
 */
class CharactersRemoteSourceImpl @Inject constructor() : BaseRemoteSource<CharactersApi>(
    CharactersApi::class.java
), CharactersRemoteSource {
    override suspend fun getAllCharacters(page: Int?): List<Character> = try {
        api.getAllCharacters(page).results
    } catch (exception: HttpException) {
        if (exception.code() == 404) listOf() else throw exception
    }
    override suspend fun getCharacters(characterIds: List<Int>) = api.getCharacters(characterIds)
    override suspend fun getCharacter(characterId: Int) = api.getCharacter(characterId)
}