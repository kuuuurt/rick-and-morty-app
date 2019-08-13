package com.kurt.example.rickandmorty.core.framework.characters.remote

import com.kurt.example.rickandmorty.core.data.characters.CharactersRemoteSource
import com.kurt.example.rickandmorty.core.framework.BaseRemoteSource
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
    override suspend fun getCharacters(page: Int?) = api.getCharacters(page).results
    override suspend fun getCharacter(characterId: Int) = api.getCharacter(characterId)
}